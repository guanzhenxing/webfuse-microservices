package network.swan.frame.db.datasource;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

/**
 * 动态数据源注册<br/>
 * 启动动态数据源请在启动类中（如SpringBootSampleApplication）
 * 添加 @Import(DynamicDataSourceRegister.class)
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;

    // 如配置文件中未指定数据源类型，使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";


    // 数据源
    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = Maps.newHashMap();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<Object, Object> targetDataSources = Maps.newHashMap();

        // 设置默认数据源
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceNames.add("dataSource");

        // 设置其他数据源
        targetDataSources.putAll(customDataSources);
        customDataSources.keySet().stream().forEach(e -> DynamicDataSourceContextHolder.dataSourceNames.add(e));

        createDynamicDataSource(registry, targetDataSources);

        LOGGER.info("Dynamic DataSource Registry");
    }

    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment env) {
        initDataSource(env);
    }

    /**
     * 创建DynamicDataSource
     *
     * @param registry
     * @param targetDataSources
     */
    private void createDynamicDataSource(BeanDefinitionRegistry registry, Map<Object, Object> targetDataSources) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);
    }

    /**
     * 初始化数据源
     *
     * @param env
     */
    private void initDataSource(Environment env) {

        // 读取主数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "datasource.");
        String dsPrefixes = propertyResolver.getProperty("names");

        //获取所有数据源名称
        String[] dataSourceNames = dsPrefixes.split(",");

        //绑定主数据源
        Arrays.stream(dataSourceNames).filter(e -> isMaster(e)).forEach(e -> bindMasterDataSource(env));

        //绑定从数据源
        Arrays.stream(dataSourceNames).filter(e -> !isMaster(e)).forEach(e -> bindSlaveDataSource(e, env));

    }


    /**
     * 创建数据源
     *
     * @param dsPros
     * @return
     */
    public DataSource buildDataSource(Map<String, Object> dsPros) {
        try {
            Object type = dsPros.get("type");
            if (type == null) type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource

            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

            String driverClassName = dsPros.get("driver-class-name").toString();
            String url = dsPros.get("url").toString();
            String username = dsPros.get("username").toString();
            String password = dsPros.get("password").toString();

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);

            return factory.build();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 绑定从数据源
     *
     * @param dsPrefix
     * @param env
     */
    private void bindSlaveDataSource(String dsPrefix, Environment env) {

        RelaxedPropertyResolver otherPropertyResolver = new RelaxedPropertyResolver(env, dsPrefix + ".datasource.");
        Map<String, Object> dsMap = this.convertData(otherPropertyResolver);
        DataSource ds = buildDataSource(dsMap);
        dataBinder(ds, env);
        customDataSources.put(dsPrefix, ds);

    }

    /**
     * 绑定默认数据源
     */
    private void bindMasterDataSource(Environment env) {

        //绑定默认数据源
        RelaxedPropertyResolver defaultPropertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        Map<String, Object> dsMap = this.convertData(defaultPropertyResolver);
        defaultDataSource = buildDataSource(dsMap);
        dataBinder(defaultDataSource, env);

    }

    /**
     * 判断是否是主数据源
     *
     * @param dataSourceName
     * @return
     */
    private boolean isMaster(String dataSourceName) {
        return "master".equals(dataSourceName);
    }

    /**
     * 转换数据
     * @param pr
     * @return
     */
    private Map<String, Object> convertData(RelaxedPropertyResolver pr) {
        Map<String, Object> dsMap = Maps.newHashMap();
        dsMap.put("type", pr.getProperty("type"));
        dsMap.put("driver-class-name", pr.getProperty("driver-class-name"));
        dsMap.put("url", pr.getProperty("url"));
        dsMap.put("username", pr.getProperty("username"));
        dsMap.put("password", pr.getProperty("password"));
        return dsMap;
    }


    private void dataBinder(DataSource dataSource, Environment env) {
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);//false
        dataBinder.setIgnoreInvalidFields(false);//false
        dataBinder.setIgnoreUnknownFields(true);//true
        if (dataSourcePropertyValues == null) {
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "offlinetrade_master.datasource").getSubProperties(".");
            Map<String, Object> values = Maps.newHashMap(rpr);
            // 排除已经设置的属性
            values.remove("type");
            values.remove("driver-class-name");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }


}
package cn.webfuse.framework.datasource;

import cn.webfuse.common.kit.ExceptionKits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源注册<br/>
 * 启动动态数据源请在启动类中（如SpringBootSampleApplication）
 * 添加 @Import(DynamicDataSourceRegister.class)
 * <p>
 * <br/>
 * 默认数据源：
 * <pre>
 *    spring.datasource.url=""
 *    spring.datasource.username=""
 *    spring.datasource.password=""
 *    spring.datasource.driver-class-name=""
 * </pre>
 * <p>
 * 新增数据源配置：
 * <pre>
 *    spring.custom.datasource.names=ds1,ds2
 *    spring.custom.datasource.ds1.driver-class-name=""
 *    spring.custom.datasource.ds1.url=""
 *    spring.custom.datasource.ds1.username=""
 *    spring.custom.datasource.ds1.password=""
 *
 *    spring.custom.datasource.ds2.driver-class-name=""
 *    spring.custom.datasource.ds2.url=""
 *    spring.custom.datasource.ds2.username=""
 *    spring.custom.datasource.ds2.password=""
 * </pre>
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    // 如配置文件中未指定数据源类型，使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    // 数据源
    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = new HashMap<>();

    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        // 将主数据源添加到targetDataSources中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceNames.add("dataSource");

        // 添加更多数据源到targetDataSources中
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceNames.add(key);
        }

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry");
    }


    /**
     * 初始化主数据源
     */
    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", env.getProperty("spring.datasource.type"));
        dsMap.put("driver-class-name", env.getProperty("spring.datasource.driver-class-name"));
        dsMap.put("url", env.getProperty("spring.datasource.url"));
        dsMap.put("username", env.getProperty("spring.datasource.username"));
        dsMap.put("password", env.getProperty("spring.datasource.password"));

        DataSource dataSource = buildDataSource(dsMap);
        //为DataSource绑定更多数据
        Bindable<DataSource> bindAble = Bindable.ofInstance(dataSource);
        defaultDataSource = Binder.get(env).bind("spring.datasource", bindAble).get();
    }


    /**
     * 初始化更多数据源
     */
    private void initCustomDataSources(Environment env) {
        String customPrefix = "spring.custom.datasource";
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        String dsPrefixes = env.getProperty(customPrefix + ".names");
        if (dsPrefixes == null) {
            return;
        }
        for (String dsPrefix : dsPrefixes.split(",")) {// 多个数据源

            // 读取主数据源
            Map<String, Object> dsMap = new HashMap<>();
            dsMap.put("type", env.getProperty(customPrefix + "." + dsPrefix + ".type"));
            dsMap.put("driver-class-name", env.getProperty(customPrefix + "." + dsPrefix + ".driver-class-name"));
            dsMap.put("url", env.getProperty(customPrefix + "." + dsPrefix + ".url"));
            dsMap.put("username", env.getProperty(customPrefix + "." + dsPrefix + ".username"));
            dsMap.put("password", env.getProperty(customPrefix + "." + dsPrefix + ".password"));

            DataSource dataSource = buildDataSource(dsMap);
            //为DataSource绑定更多数据
            Bindable<DataSource> bindAble = Bindable.ofInstance(dataSource);
            DataSource customDataSource = Binder.get(env).bind("spring.custom.datasource", bindAble).get();
            customDataSources.put(dsPrefix, customDataSource);
        }
    }

    /**
     * 创建DataSource
     */
    @SuppressWarnings("unchecked")
    private DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null) {
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
            }

            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

            String driverClassName = dsMap.get("driver-class-name").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            throw ExceptionKits.unchecked(e);
        }
    }

}
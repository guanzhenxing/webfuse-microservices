package network.swan.core.utils.mapper;

import network.swan.core.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现Bean与Map之间的转换
 */
public class BeanMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private BeanMapper() {
    }

    /**
     * 将Map对象转换成Bean对象
     *
     * @param map 待转换的Map对象
     * @param obj 转换后的Bean对象
     */
    // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
    public static void convertMapToBean(Map<String, Object> map, Object obj) {

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }

            }

        } catch (Exception e) {
            LOGGER.error("transMap2Bean Error " + e);
            ExceptionUtil.throwException(e);
        }

        return;

    }

    /**
     * 将Bean对象转换成Map对象
     *
     * @param bean 待转的Bean对象
     * @return 转换后的Map对象
     */
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> convertBeanToMap(Object bean) {

        if (bean == null) {
            return null;
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(bean);
                    returnMap.put(key, value);
                }
            }

        } catch (Exception e) {
            LOGGER.error("convertBeanToMap Error " + e);
            ExceptionUtil.throwException(e);
        }
        return returnMap;

    }
}

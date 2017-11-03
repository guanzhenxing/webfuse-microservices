package network.swan.frame.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Spring帮助类，实现了BeanFactoryPostProcessor接口
 */
public class SpringContextHolder implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory; // Spring应用上下文环境

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringContextHolder.beanFactory = beanFactory;
    }

    /**
     * 获得spring应用上下文
     *
     * @return
     */
    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 获得对象
     *
     * @param name 对象的名字
     * @return 以name为名字注册的实例对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获得对象
     *
     * @param requiredType 对象的类型
     * @return 类型为clz的对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        return beanFactory.getBean(requiredType);
    }

    /**
     * 获得对象
     *
     * @param name         对象的名称
     * @param requiredType 对象的类型
     * @return
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return beanFactory.getBean(name, requiredType);
    }

    /**
     * 是否包含该名字的bean对象
     *
     * @param name
     * @return
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * 是否单例
     *
     * @param name
     * @return
     */
    public static boolean isSingleton(String name) {
        return beanFactory.isSingleton(name);
    }

    /**
     * 获得类型
     *
     * @param name
     * @return Class 注册对象的类型
     */
    public static Class<?> getType(String name) {
        return beanFactory.getType(name);
    }

}

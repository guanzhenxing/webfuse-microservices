package network.swan.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.URL;

/**
 * 类操作工具
 */
public class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ClassUtil() {
    }

    /**
     * 获得类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取类路径
     *
     * @return
     */
    public static String getClassPath() {
        String classpath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }

    /**
     * 加载类
     *
     * @param className 类名（含包名）
     * @return
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 加载类
     *
     * @param className     类名（含包名）
     * @param isInitialized 是否初始化
     * @return
     */
    private static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载类出错！", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static String getOriginalClassName(Class<?> targetClass) {
        return getOriginalClass(targetClass).getName();
    }

    public static Class<?> getOriginalClass(Class<?> targetClass) {
        String className = targetClass.getName();
        if (className.indexOf("$$EnhancerBy") > 0) {
            return targetClass.getSuperclass();
        }
        return targetClass;
    }

    public static String getOriginalClassName(Object object) {
        return getOriginalClassName(object.getClass());
    }

    public static String getSimpleOriginalClassName(Object object) {
        String fullClassName = getOriginalClassName(object);
        int lastNamespace = fullClassName.lastIndexOf('.');
        if (lastNamespace > -1) {
            return fullClassName.substring(lastNamespace + 1);
        }
        return fullClassName;
    }

}

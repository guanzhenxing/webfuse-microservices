package cn.webfuse.data.mybatis.utils;

import cn.webfuse.data.mybatis.mapper.BaseProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProviderHelper {

    /**
     * 注册的通用Mapper接口
     */
    private Map<Class<?>, BaseProvider> registerMapper = new ConcurrentHashMap<>();

    public ProviderHelper() {
    }

    /**
     * 注册通用Mapper接口
     *
     * @param mapperClass
     * @throws Exception
     */
    public void registerMapper(Class<?> mapperClass) {
        if (registerMapper.get(mapperClass) == null) {
            registerMapper.put(mapperClass, fromMapperClass(mapperClass));
        } else {
            throw new RuntimeException("");
        }
    }

    /**
     * 注册通用Mapper接口
     *
     * @param mapperClass
     * @throws Exception
     */
    public void registerMapper(String mapperClass) {
        try {
            registerMapper(Class.forName(mapperClass));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("");
        }
    }

    private BaseProvider fromMapperClass(Class<?> mapperClass) {

        Method[] methods = mapperClass.getMethods();
        Class<?> providerClass = null;
        Set<String> methodSet = new HashSet<>();

        for (Method method : methods) {
            Class<?> typeClass = null;
            if (method.isAnnotationPresent(SelectProvider.class)) {
                SelectProvider provider = method.getAnnotation(SelectProvider.class);
                typeClass = provider.type();
                methodSet.add(method.getName());
            } else if (method.isAnnotationPresent(InsertProvider.class)) {
                InsertProvider provider = method.getAnnotation(InsertProvider.class);
                typeClass = provider.type();
                methodSet.add(method.getName());
            } else if (method.isAnnotationPresent(DeleteProvider.class)) {
                DeleteProvider provider = method.getAnnotation(DeleteProvider.class);
                typeClass = provider.type();
                methodSet.add(method.getName());
            } else if (method.isAnnotationPresent(UpdateProvider.class)) {
                UpdateProvider provider = method.getAnnotation(UpdateProvider.class);
                typeClass = provider.type();
                methodSet.add(method.getName());
            }
            if (providerClass == null) {
                providerClass = typeClass;
            } else if (providerClass != typeClass) {
                throw new RuntimeException("");
            }
        }

        BaseProvider baseProvider;
        try {
            baseProvider = (BaseProvider) providerClass.getConstructor(Class.class).newInstance(mapperClass);
        } catch (Exception e) {
            throw new RuntimeException("");
        }

        return baseProvider;
    }

}

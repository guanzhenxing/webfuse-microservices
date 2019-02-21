package cn.webfuse.framework.data.mybatis.ext.camelcase;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * CamelCaseMappingWrapper继承MapWrapper，重写findProperty。
 */
public class CamelCaseMappingWrapper extends MapWrapper {
    public CamelCaseMappingWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping) {
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name.toLowerCase());
        }
        return name;
    }
}

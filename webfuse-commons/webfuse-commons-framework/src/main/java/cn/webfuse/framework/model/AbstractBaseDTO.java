package cn.webfuse.framework.model;

import cn.webfuse.framework.core.kit.mapper.BeanMapper;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractBaseDTO implements Serializable {


    public Map<String, Object> toMap(boolean ignoreParent, boolean ignoreEmptyValue, String... ignoreProperties) {
        return BeanMapper.convertBeanToMap(this, ignoreParent, ignoreEmptyValue, ignoreProperties);
    }

    public Map<String, Object> toMap() {
        return toMap(false, false, new String[0]);
    }

    public <E extends AbstractBaseDTO> E fromMap(Map<String, Object> map) {
        return (E) BeanMapper.convertMapToBean(map, AbstractBaseDTO.class);
    }


}

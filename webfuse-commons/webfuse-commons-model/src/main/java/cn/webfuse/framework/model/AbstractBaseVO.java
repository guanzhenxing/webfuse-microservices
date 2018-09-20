package cn.webfuse.framework.model;

import cn.webfuse.common.kit.mapper.BeanMapper;
import cn.webfuse.common.kit.mapper.JsonMapper;
import cn.webfuse.common.kit.reflect.ReflectionKits;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractBaseVO implements Serializable {


    public <E extends AbstractBaseDTO> E toDto() {
        return (E) BeanMapper.map(this, AbstractBaseDTO.class);
    }

    public String toJson() {
        return JsonMapper.defaultMapper().toJson(this);
    }

    public void reset() {
        FieldUtils.getAllFieldsList(this.getClass())
                .stream()
                .forEach(field ->
                        ReflectionKits.invokeSetter(this, field.getName(), null)
                );
    }

    public <E extends AbstractBaseVO> E fromMap(Map<String, Object> map) {
        return (E) BeanMapper.convertMapToBean(map, AbstractBaseVO.class);
    }


}

package cn.webfuse.framework.model;

import cn.webfuse.common.kit.mapper.BeanMapper;

import java.io.Serializable;
import java.util.Date;

/**
 * DO的抽象基类
 */
public abstract class AbstractBaseDO implements Serializable {

    public <E extends AbstractBaseDO> E fromEntity(AbstractBaseEntity entity) {
        return (E) BeanMapper.map(entity, AbstractBaseDO.class);
    }

    public <E extends AbstractBaseEntity> E toEntity() {
        return (E) BeanMapper.map(this, AbstractBaseEntity.class);
    }

}

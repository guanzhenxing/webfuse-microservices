package cn.webfuse.framework.model;

import cn.webfuse.framework.core.kit.mapper.BeanMapper;

import java.io.Serializable;

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

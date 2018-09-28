package cn.webfuse.framework.model;

import cn.webfuse.common.kit.mapper.BeanMapper;

import java.io.Serializable;
import java.util.Date;

/**
 * DO的抽象基类
 *
 * @param <T> ID的类型
 */
public abstract class AbstarctBaseDO<T> implements Serializable {

    protected T id;
    protected Date createTime;
    protected Date updateTime;
    protected Boolean deleted = false;

    public abstract T getId();

    public abstract void setId(T id);

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public <E extends AbstarctBaseDO> E fromEntity(AbstractBaseEntity entity) {
        return (E) BeanMapper.map(entity, AbstarctBaseDO.class);
    }

    public <E extends AbstractBaseEntity> E toEntity() {
        return (E) BeanMapper.map(this, AbstractBaseEntity.class);
    }

}

package cn.webfuse.framework.model;

import java.io.Serializable;

public abstract class AbstractBaseEntity<T> implements Serializable {

    protected T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}

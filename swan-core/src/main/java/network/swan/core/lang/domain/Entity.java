package network.swan.core.lang.domain;

import java.io.Serializable;

/**
 * 实体基类
 */
public class Entity implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
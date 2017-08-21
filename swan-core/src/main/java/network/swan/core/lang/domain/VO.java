package network.swan.core.lang.domain;

import java.io.Serializable;

/**
 * 值对象的基类
 */
public class VO implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

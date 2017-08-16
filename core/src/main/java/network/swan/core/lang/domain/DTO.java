package network.swan.core.lang.domain;

import java.io.Serializable;

/**
 * 数据传输对象的基类
 */
public class DTO implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}


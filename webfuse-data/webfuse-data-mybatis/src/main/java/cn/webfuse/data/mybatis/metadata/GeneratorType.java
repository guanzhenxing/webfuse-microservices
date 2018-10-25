package cn.webfuse.data.mybatis.metadata;

import lombok.Getter;

@Getter
public enum GeneratorType {

    /**
     * 该类型为未设置主键类型，此时需要用户自己手动输入
     */
    NONE("NONE"),
    /**
     * 数据库ID自增
     */
    IDENTITY("IDENTITY"),
    /**
     * 使用序列
     */
    SEQUENCE("SEQUENCE"),
    /**
     * 全局唯一ID (idWorker)
     */
    ID_WORKER("ID_WORKER"),
    /**
     * 全局唯一ID (UUID)
     */
    UUID("UUID"),

    /**
     * ObjectId
     */
    OBJECT_ID("OBJECT_ID"),

    ;


    private String key;

    GeneratorType(String key) {
        this.key = key;
    }
}

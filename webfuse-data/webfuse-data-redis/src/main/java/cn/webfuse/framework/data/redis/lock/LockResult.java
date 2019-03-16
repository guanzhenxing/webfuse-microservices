package cn.webfuse.framework.data.redis.lock;

import lombok.Data;
import lombok.ToString;

/**
 * LockResult
 * <p>
 * From: org.itkk.udf.cache.redis.lock.LockResult
 */
@Data
@ToString
public class LockResult {
    /**
     * lock
     */
    private Boolean lock = false;

    /**
     * value
     */
    private String value;
}

package cn.webfuse.framework.security.service.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

public class NonceCache {

    private static LoadingCache<String, Byte> nonceCache;
    private static final byte NONCE_EXIST = 33;

    @Value("${webfuse.security.nonce-expire:300000}")
    private long nonceExpire;   //默认5分钟

    public NonceCache() {
        initNonceCache();
    }

    private void initNonceCache() {
        nonceCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(nonceExpire, TimeUnit.MICROSECONDS)
                .build(new CacheLoader<String, Byte>() {
                    @Override
                    public Byte load(String key) throws Exception {
                        return NONCE_EXIST;
                    }
                });
    }

    /**
     * 获取nonceCache，用于查询nonce串是否存在
     *
     * @return
     */
    public LoadingCache<String, Byte> getNonceCache() {
        return nonceCache;
    }

    public boolean existNonce(String nonce) {
        Byte exist = nonceCache.getIfPresent(nonce);
        if (null == exist) {
            nonceCache.put(nonce, NONCE_EXIST);
            return false;
        } else {
            return true;
        }
    }


}

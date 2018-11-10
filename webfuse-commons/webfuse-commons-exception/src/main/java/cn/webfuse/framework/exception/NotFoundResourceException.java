package cn.webfuse.framework.exception;

import cn.webfuse.framework.core.exception.AbstractBizException;

/**
 * 没有发现资源异常
 */
public class NotFoundResourceException extends AbstractBizException {

    public NotFoundResourceException(int status, String errorCode, String message, Throwable throwable, String developerMessage) {
        super(status, errorCode, message, throwable, developerMessage);
    }

    public NotFoundResourceException(int status, String errorCode, String message, Throwable throwable) {
        super(status, errorCode, message, throwable);
    }

    public NotFoundResourceException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }
}

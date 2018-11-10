package cn.webfuse.framework.exception;

import cn.webfuse.framework.core.exception.AbstractBizException;

/**
 * 错误的参数异常
 */
public class BadParamException extends AbstractBizException {

    public BadParamException(int status, String errorCode, String message, Throwable throwable, String developerMessage) {
        super(status, errorCode, message, throwable, developerMessage);
    }

    public BadParamException(int status, String errorCode, String message, Throwable throwable) {
        super(status, errorCode, message, throwable);
    }

    public BadParamException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }
}

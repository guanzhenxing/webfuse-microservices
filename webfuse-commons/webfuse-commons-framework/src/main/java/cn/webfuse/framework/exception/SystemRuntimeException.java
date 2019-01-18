package cn.webfuse.framework.exception;

import cn.webfuse.framework.core.exception.AbstractBizException;

/**
 * 系统运行时异常
 * @author Jesen
 */
public class SystemRuntimeException extends AbstractBizException {

    public SystemRuntimeException(int status, String errorCode, String message, Throwable throwable, String developerMessage) {
        super(status, errorCode, message, throwable, developerMessage);
    }

    public SystemRuntimeException(int status, String errorCode, String message, Throwable throwable) {
        super(status, errorCode, message, throwable);
    }

    public SystemRuntimeException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }
}

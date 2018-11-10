package cn.webfuse.framework.jpa.exception;

import cn.webfuse.framework.core.exception.AbstractBizException;

/**
 * Jpa数据异常
 */
public class JpaExecuteException extends AbstractBizException {

    public JpaExecuteException(int status, String code, String message, Throwable throwable, String developerMessage) {
        super(status, code, message, throwable, developerMessage);
    }

    public JpaExecuteException(int status, String code, String message, Throwable throwable) {
        super(status, code, message, throwable);
    }

    public JpaExecuteException(int status, String code, String message) {
        super(status, code, message);
    }

    public JpaExecuteException() {
    }

    public JpaExecuteException(String message) {
        super(message);
    }
}

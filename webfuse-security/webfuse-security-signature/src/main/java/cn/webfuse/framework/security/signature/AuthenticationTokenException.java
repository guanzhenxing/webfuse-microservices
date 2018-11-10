package cn.webfuse.framework.security.signature;

import cn.webfuse.framework.core.exception.AbstractBizException;

public class AuthenticationTokenException extends AbstractBizException {

    public AuthenticationTokenException(int status, String errorCode, String message, Throwable throwable, String developerMessage) {
        super(status, errorCode, message, throwable, developerMessage);
    }

    public AuthenticationTokenException(int status, String errorCode, String message, Throwable throwable) {
        super(status, errorCode, message, throwable);
    }

    public AuthenticationTokenException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }
}

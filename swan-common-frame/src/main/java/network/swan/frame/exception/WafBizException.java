package network.swan.frame.exception;

/**
 * 业务异常
 * Created by guanzhenxing on 2017/11/3.
 */
public class WafBizException extends WafBaseException {

    private String errorCode;
    private String message;
    private Throwable throwable;

    public WafBizException(String errorCode, String message, Throwable throwable) {
        this.errorCode = errorCode;
        this.message = message;
        this.throwable = throwable;
    }

    public WafBizException(String errorCode, Throwable throwable) {
        this.errorCode = errorCode;
        this.throwable = throwable;
    }

    public WafBizException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public WafBizException(String errorCode) {
        this.errorCode = errorCode;
    }

    public WafBizException(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}

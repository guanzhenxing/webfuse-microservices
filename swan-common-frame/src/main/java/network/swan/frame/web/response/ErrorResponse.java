package network.swan.frame.web.response;

/**
 * Created by guanzhenxing on 2017/11/3.
 */

/**
 * 错误信息的Response
 * TODO: 后期需要加入微服务名等；可以试着将这个写在RestfulExceptionHandler中，作为一个内部类
 */
public class ErrorResponse {

    private String errorCode;
    private String message;
    private String exceptionTrace;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionTrace() {
        return exceptionTrace;
    }

    public void setExceptionTrace(String exceptionTrace) {
        this.exceptionTrace = exceptionTrace;
    }
}

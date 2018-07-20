package network.swan.frame.exception;

/**
 * Created by guanzhenxing on 2017/11/20.
 */
public class NoResourceException extends WafBizException {
    public NoResourceException(String errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }

    public NoResourceException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }

    public NoResourceException(String errorCode, String message) {
        super(errorCode, message);
    }

    public NoResourceException(String errorCode) {
        super(errorCode);
    }

    public NoResourceException(Throwable throwable) {
        super(throwable);
    }
}

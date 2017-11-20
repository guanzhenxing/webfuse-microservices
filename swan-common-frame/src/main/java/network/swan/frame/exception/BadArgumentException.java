package network.swan.frame.exception;

/**
 * Created by guanzhenxing on 2017/11/20.
 */
public class BadArgumentException extends WafBizException {
    public BadArgumentException(String errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }

    public BadArgumentException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BadArgumentException(String errorCode) {
        super(errorCode);
    }
}

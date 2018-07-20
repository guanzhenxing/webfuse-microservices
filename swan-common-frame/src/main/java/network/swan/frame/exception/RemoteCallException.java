package network.swan.frame.exception;

/**
 * 远程调用异常
 * Created by guanzhenxing on 2017/11/3.
 */
public class RemoteCallException extends WafBizException {

    public RemoteCallException(String errorCode, String message) {
        super(errorCode, message, null);
    }


    public RemoteCallException(String errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }

}

package network.swan.frame.web.handler;

import network.swan.frame.exception.WafBizException;
import network.swan.frame.web.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

/**
 * Created by guanzhenxing on 2017/11/3.
 * Restful异常处理
 */
@ControllerAdvice
public class RestfulExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 处理运行时异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    ResponseEntity<Object> handleRuntimeException(ServletWebRequest request, RuntimeException ex) {
        ErrorResponse bodyOfResponse = buildErrorResponseBody(request, ex, "INTERNAL_SERVER_ERROR");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * 处理业务异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {WafBizException.class})
    ResponseEntity<Object> handleWafBizException(ServletWebRequest request, WafBizException ex) {
        ErrorResponse bodyOfResponse = buildErrorResponseBody(request, ex, "INTERNAL_SERVER_ERROR");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    /**
     * 构建ErrorResponse
     *
     * @param request
     * @param ex
     * @param errorCode
     * @return
     */
    private ErrorResponse buildErrorResponseBody(ServletWebRequest request, RuntimeException ex, String errorCode) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setMessage(ex.getMessage());
        if ("dev".equalsIgnoreCase(env)) {
            errorResponse.setExceptionTrace(Arrays.toString(ex.getStackTrace()));
        }
        return errorResponse;
    }


}
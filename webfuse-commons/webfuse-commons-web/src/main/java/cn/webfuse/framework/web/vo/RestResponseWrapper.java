package cn.webfuse.framework.web.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * Restful返回值的封装。继承了ResponseEntity。
 *
 * @param <T> 返回的实体对象
 */
public class RestResponseWrapper<T> extends ResponseEntity<T> {

    public RestResponseWrapper(HttpStatus status) {
        super(status);
    }

    public RestResponseWrapper(T body, HttpStatus status) {
        super(body, status);
    }

    public RestResponseWrapper(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public RestResponseWrapper(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public static <T> RestResponseWrapper<T> buildPostResponse(T body) {
        return new RestResponseWrapper(body, HttpStatus.CREATED);
    }

    public static <T> RestResponseWrapper<T> buildGetResponse(T body) {
        return new RestResponseWrapper<>(body, HttpStatus.OK);
    }

    public static <T> RestResponseWrapper<T> buildPutResponse(T body) {
        return new RestResponseWrapper<>(body, HttpStatus.OK);
    }

    public static <T> RestResponseWrapper<T> buildPatchResponse(T body) {
        return new RestResponseWrapper<>(body, HttpStatus.OK);
    }

    public static <T> RestResponseWrapper<T> buildDeleteResponse() {
        return new RestResponseWrapper<>(HttpStatus.NO_CONTENT);
    }

}

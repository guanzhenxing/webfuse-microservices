package network.swan.frame.support.web.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Enumeration;
import java.util.Map;

/**
 * 扩展RestTemplate
 * Created by guanzhenxing on 2017/10/31.
 */
public class RestTemplateSupport {

    private RestTemplate restTemplate;

    public RestTemplateSupport(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static RestTemplateSupport build(RestTemplate restTemplate) {
        return new RestTemplateSupport(restTemplate);
    }

    /**
     * 构建HttpHeader
     *
     * @param request
     * @return
     */
    public HttpHeaders buildHttpHeaders(HttpServletRequest request) {

        HttpHeaders httpHeaders = new HttpHeaders();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            httpHeaders.add(headerName, request.getHeader(headerName));
        }

        return httpHeaders;
    }

    /**
     * 添加HttpHeader
     *
     * @param httpHeaders
     * @param headerName
     * @param headerValue
     * @return
     */
    public HttpHeaders addHttpHeaders(HttpHeaders httpHeaders, String headerName, String headerValue) {
        httpHeaders.add(headerName, headerValue);
        return httpHeaders;
    }

    /**
     * 添加HttpHeader
     *
     * @param httpHeaders
     * @param headers
     * @return
     */
    public HttpHeaders addHttpHeaders(HttpHeaders httpHeaders, Map<String, String> headers) {

        headers.keySet().stream().forEach(key -> {
            String value = headers.get(key);
            httpHeaders.add(key, value);
        });

        return httpHeaders;
    }

    /**
     * 带有自定义HttpHeader的GET请求
     *
     * @param url
     * @param headers
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     */
    public <T> T getForObjectWithHeader(String url, HttpHeaders headers, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的GET请求
     *
     * @param url
     * @param headers
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     */
    public <T> T getForObjectWithHeader(String url, HttpHeaders headers, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的GET请求
     *
     * @param url
     * @param headers
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> T getForObjectWithHeader(URI url, HttpHeaders headers, Class<T> responseType)
            throws RestClientException {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的POST请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T postForObjectWithHeader(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        HttpEntity entity = new HttpEntity(requestBody, headers);
        return restTemplate.postForObject(url, entity, responseType, uriVariables);
    }

    /**
     * 带有自定义HttpHeader的POST请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T postForObjectWithHeader(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        HttpEntity entity = new HttpEntity(requestBody, headers);
        return restTemplate.postForObject(url, entity, responseType, uriVariables);
    }

    /**
     * 带有自定义HttpHeader的POST请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T postForObjectWithHeader(URI url, HttpHeaders headers, Object requestBody, Class<T> responseType) throws RestClientException {
        HttpEntity entity = new HttpEntity(requestBody, headers);
        return restTemplate.postForObject(url, entity, responseType);
    }


    /**
     * 带有自定义HttpHeader的PUT请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T putForObjectWithHeader(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        HttpEntity entity = new HttpEntity(requestBody, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的PUT请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T putForObjectWithHeader(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        HttpEntity entity = new HttpEntity(requestBody, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的PUT请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T putForObjectWithHeader(URI url, HttpHeaders headers, Object requestBody, Class<T> responseType) throws RestClientException {
        HttpEntity entity = new HttpEntity(requestBody, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, responseType);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的PATCH请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T patchForObjectWithHeader(String url, HttpHeaders headers, Object requestBody, Class<T> responseType,
                                          Object... uriVariables) throws RestClientException {
        HttpEntity entity = new HttpEntity(requestBody, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, entity, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的PATCH请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T patchForObjectWithHeader(String url, HttpHeaders headers, Object requestBody, Class<T> responseType,
                                          Map<String, ?> uriVariables) throws RestClientException {

        HttpEntity entity = new HttpEntity(requestBody, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, entity, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的PATCH请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param responseType
     * @param <T>
     * @return
     * @throws RestClientException
     */
    public <T> T patchForObjectWithHeader(URI url, HttpHeaders headers, Object requestBody, Class<T> responseType)
            throws RestClientException {

        HttpEntity entity = new HttpEntity(requestBody, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, entity, responseType);
        return responseEntity.getBody();
    }

    /**
     * 带有自定义HttpHeader的DELETE请求
     *
     * @param url
     * @param headers
     * @param uriVariables
     * @throws RestClientException
     */
    public void deleteWithHeader(String url, HttpHeaders headers, Object... uriVariables) throws RestClientException {
        HttpEntity entity = new HttpEntity(headers);

        restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, uriVariables);
    }

    /**
     * 带有自定义HttpHeader的DELETE请求
     *
     * @param url
     * @param headers
     * @param uriVariables
     * @throws RestClientException
     */
    public void deleteWithHeader(String url, HttpHeaders headers, Map<String, ?> uriVariables) throws RestClientException {
        HttpEntity entity = new HttpEntity(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, uriVariables);
    }

    /**
     * 带有自定义HttpHeader的DELETE请求
     *
     * @param url
     * @param headers
     * @throws RestClientException
     */
    public void deleteWithHeader(URI url, HttpHeaders headers) throws RestClientException {
        HttpEntity entity = new HttpEntity(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
    }


}
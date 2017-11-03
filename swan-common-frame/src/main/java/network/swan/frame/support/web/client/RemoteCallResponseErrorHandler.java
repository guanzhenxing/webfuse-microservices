package network.swan.frame.support.web.client;

import network.swan.core.utils.mapper.JsonMapper;
import network.swan.frame.exception.RemoteCallException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.Map;

/**
 * 使用：
 * <pre>
 * RestTemplate restTemplate = new RestTemplate();
 * restTemplate.setErrorHandler(new CustomResponseErrorHandler());
 * </pre>
 * <p>
 * Created by guanzhenxing on 2017/11/3.
 */
public class RemoteCallResponseErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        String theString = IOUtils.toString(response.getBody());
        Map<String, String> error = (Map<String, String>) JsonMapper.fromJsonString(theString, Map.class);
        String errorCode = error.get("errorCode");
        String message = error.get("message");

        throw new RemoteCallException(errorCode, message, null);

    }
}
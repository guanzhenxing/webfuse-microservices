package network.swan.auth.web.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guanzhenxing on 2017/9/23.
 */
@RestController
public class TokenController {

    public static final String REST_SERVICE_URI = "http://localhost:8001";

    public static final String AUTH_SERVER_URI = "http://localhost:8001/oauth/token";

    public static final String QPM_PASSWORD_GRANT = "?grant_type=password&username=admin&password=password";

    public static final String QPM_ACCESS_TOKEN = "?access_token=";


    private static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private static HttpHeaders getHeadersWithClientCredentials() {
        String plainClientCredentials = "trusted-app:secret";
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

        HttpHeaders headers = getHeaders();
        headers.add("Authorization", "Basic " + base64ClientCredentials);
        return headers;
    }

    @GetMapping("/token")
    public Map<String, Object> getToken() {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(getHeadersWithClientCredentials());
        ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI + QPM_PASSWORD_GRANT, HttpMethod.POST, request, Object.class);
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();


        System.out.println(map);
//        AuthTokenInfo tokenInfo = null;


        return map;
    }


}

package spring.grpc.client;

import com.google.common.collect.Lists;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import spring.grpc.entry.Entry;
import spring.grpc.response.Response;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
public class RestClient {

    private String endpoint;

    public RestClient(String endpoint) {
        this.endpoint = endpoint;
    }

    private HttpEntity<?> getClientHeaders(Object postData) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set(HttpHeaders.ACCEPT, "application/json");
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        String authHeader = "Bearer FAJN00KWU=";
        headers.set(HttpHeaders.AUTHORIZATION, authHeader);

        if (postData == null) {
            return new HttpEntity<>(headers);
        }
        return new HttpEntity<>(postData, headers);
    }

    public void test(Entry entry) {
        try {
            RestTemplate restTemplate = new RestTemplate(Lists.newArrayList(new MappingJackson2HttpMessageConverter()));
            restTemplate.exchange(endpoint.concat("/test"), HttpMethod.POST, getClientHeaders(entry), Response.class);
        } catch (ResourceAccessException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

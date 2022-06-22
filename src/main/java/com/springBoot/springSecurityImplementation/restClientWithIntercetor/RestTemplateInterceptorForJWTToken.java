package com.springBoot.springSecurityImplementation.restClientWithIntercetor;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

public class RestTemplateInterceptorForJWTToken implements ClientHttpRequestInterceptor {

    private String JWTToken;

    public RestTemplateInterceptorForJWTToken(String JWTToken) {
        this.JWTToken = JWTToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("Authorization",JWTToken);

        ClientHttpResponse response = execution.execute(request, body);
        response.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        return response;
    }
}

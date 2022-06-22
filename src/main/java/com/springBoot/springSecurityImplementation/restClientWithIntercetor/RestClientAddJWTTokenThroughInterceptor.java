package com.springBoot.springSecurityImplementation.restClientWithIntercetor;

import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestClientAddJWTTokenThroughInterceptor {

    private static RestTemplate restTemplate = new RestTemplate();

    private static String JWTToken;

    private static String getTokenSignIn = "http://localhost:8080/api/task/signin";
    private static String getAllUsers = "http://localhost:8080/api/task/get";


    public static void main(String[] args) {
        callSignIn_ToGetTokenAPI();

        callGetAllUsersAPI();

    }


    private static void callSignIn_ToGetTokenAPI() {
        String requestJson = "{\"userName\":\"Abc\",\"userPassword\":\"anas\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

        JWTToken = restTemplate.postForObject(getTokenSignIn, entity, String.class);
        System.out.println("token = " + JWTToken);

        if (JWTToken == null)
            throw new UsernameNotFoundException("username : ");
        System.out.println("ADDING JWT TOKEN TO INTERCEPTOR");

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

        if (CollectionUtils.isEmpty(interceptors))
            interceptors = new ArrayList<>();

        interceptors.add(new RestTemplateInterceptorForJWTToken(JWTToken));

        restTemplate.setInterceptors(interceptors);

    }


    private static void callGetAllUsersAPI() {

        //header value is added through interceptor like 1) media type , 2) JWT Token in RestTemplateInterceptorForJWTToken class

        ResponseEntity<String> result = restTemplate.exchange(getAllUsers, HttpMethod.GET, null, String.class);

        System.out.println(result.getBody());
    }

}


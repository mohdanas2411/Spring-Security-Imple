package com.springBoot.springSecurityImplementation.clients;

import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.entity.User_One;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.util.Arrays;

public class RestClient {

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

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

        JWTToken = restTemplate.postForObject(getTokenSignIn,entity,String.class);
        System.out.println("token = " +JWTToken);
    }


    private static void callGetAllUsersAPI() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization",JWTToken);

        HttpEntity<String> parameters = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(getAllUsers, HttpMethod.GET, parameters, String.class);

        System.out.println(result.getBody());
    }

}

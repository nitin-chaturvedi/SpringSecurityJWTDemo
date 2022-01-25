package com.example.SpringSecurityJWTDemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.SpringSecurityJWTDemo.models.AuthenticationRequest;
import com.example.SpringSecurityJWTDemo.models.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerAcceptanceTest {


  @LocalServerPort
  int serverPort;

  private RestTemplate restTemplate;
  private String baseUrl;

  @BeforeEach
  private void setUp() {
    restTemplate = new RestTemplate();
    baseUrl = "http://localhost:" + serverPort;
  }

  @Test
  public void testHelloApi() {
    int status = 0;
    try {
      restTemplate.getForEntity(baseUrl + "/hello", String.class);
    } catch (Exception e) {
      status = ((HttpClientErrorException) e).getStatusCode().value();
    }
    assertEquals(403, status);
  }

  @Test
  public void testAuthenticateApi() throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.add("user-agent", "Application");

    ResponseEntity<AuthenticationResponse> responseEntity = restTemplate.postForObject(
        baseUrl + "/authenticate",
        new HttpEntity<String>(
          new ObjectMapper().writeValueAsString(
            AuthenticationRequest.builder().username("string").password("string").build()),
          headers
        ),
        ResponseEntity.class);
    assertEquals(200, responseEntity.getStatusCodeValue());

  }
}

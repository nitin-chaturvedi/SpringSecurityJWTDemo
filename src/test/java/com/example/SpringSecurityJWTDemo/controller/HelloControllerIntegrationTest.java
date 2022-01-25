package com.example.SpringSecurityJWTDemo.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.SpringSecurityJWTDemo.models.AuthenticationRequest;
import com.example.SpringSecurityJWTDemo.models.MyUserDetails;
import com.example.SpringSecurityJWTDemo.service.MyUserDetailsService;
import com.example.SpringSecurityJWTDemo.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class HelloControllerIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private MyUserDetailsService myUserDetailService;
  @MockBean
  JwtUtil jwtUtil;

  @MockBean
  AuthenticationManager authenticationManager;

  @Test
  public void unauthorizedAccess() throws Exception {
    mockMvc.perform(get("/hello"))
      .andExpect(status().is(403));
    System.out.println("finished");
  }

  @Test
  public void testCreateAuthToken() throws Exception {
    MyUserDetails ud = new MyUserDetails();
    ud.setUsername("string");
    when(myUserDetailService.loadUserByUsername(anyString())).thenReturn(ud);
    mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
        .content(
      new ObjectMapper().writeValueAsString(
        AuthenticationRequest.builder().username("string").password("string").build())
      ))
      .andExpect(status().isOk());
  }

}

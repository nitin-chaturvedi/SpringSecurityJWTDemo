package com.example.SpringSecurityJWTDemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.SpringSecurityJWTDemo.models.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.AuthenticationManager;

@WebMvcTest
class HelloControllerUnitTest {

  @Test
  public void unitTestHello() {
    assertEquals("Hello guys, JWT Auth is working.", new HelloController().hello());
  }

  @Test
  public void testCreateAuthToken() {
    AuthenticationManager mockAuthManager = Mockito.mock(AuthenticationManager.class);
    assertThrows(Exception.class, () -> {
      new HelloController().createAuthToken(new AuthenticationRequest("", ""));
    });

  }

}

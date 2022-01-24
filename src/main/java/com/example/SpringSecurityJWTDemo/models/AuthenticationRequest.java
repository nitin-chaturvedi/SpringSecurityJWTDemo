package com.example.SpringSecurityJWTDemo.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

  private String username;
  private String password;

  public AuthenticationRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }
}

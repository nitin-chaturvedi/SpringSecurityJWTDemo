package com.example.SpringSecurityJWTDemo.controller;

import com.example.SpringSecurityJWTDemo.util.JwtUtil;
import com.example.SpringSecurityJWTDemo.models.AuthenticationRequest;
import com.example.SpringSecurityJWTDemo.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  UserDetailsService userDetailService;
  @Autowired
  JwtUtil jwtUtil;

  @RequestMapping("/hello")
  public String hello(){
    return "Hello guys";
  }

  @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
  public ResponseEntity<AuthenticationResponse> createAuthToken(
    @RequestBody AuthenticationRequest authReq
    ) throws Exception{
  try {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword())
    );
  } catch(BadCredentialsException e) {
    throw new Exception("Incorrect username or password",e);
  }
  final UserDetails userDetail = userDetailService.loadUserByUsername(authReq.getUsername());
  final String jwt = jwtUtil.generateToken(userDetail);
  return ResponseEntity.ok(new AuthenticationResponse(jwt));

  }
}

package com.example.SpringSecurityJWTDemo.controller;

import com.example.SpringSecurityJWTDemo.models.AuthenticationRequest;
import com.example.SpringSecurityJWTDemo.models.AuthenticationResponse;
import com.example.SpringSecurityJWTDemo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  UserDetailsService userDetailService;
  @Autowired
  JwtUtil jwtUtil;

  Logger logger = LoggerFactory.getLogger(HelloController.class);

  @RequestMapping("/hello")
  public String hello(){
    return "Hello guys, JWT Auth is working.";
  }

  @PostMapping(value = "/authenticate")
  public ResponseEntity<AuthenticationResponse> createAuthToken(
    @RequestBody AuthenticationRequest authReq
    ) throws Exception{
  try {
    logger.info("Started authentication : -");
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword())
    );
  } catch(BadCredentialsException e) {
    logger.error("Authentication failed : -");
    throw new Exception("Incorrect username or password",e);
  }
  final UserDetails userDetail = userDetailService.loadUserByUsername(authReq.getUsername());
  final String jwt = jwtUtil.generateToken(userDetail);
  return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

}

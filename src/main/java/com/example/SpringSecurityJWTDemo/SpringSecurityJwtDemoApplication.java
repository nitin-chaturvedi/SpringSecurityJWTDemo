package com.example.SpringSecurityJWTDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2 Configured with seperate for more detailed config handling
public class SpringSecurityJwtDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityJwtDemoApplication.class, args);
  }

}

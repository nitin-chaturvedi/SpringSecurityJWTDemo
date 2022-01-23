package com.example.SpringSecurityJWTDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Collections;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"!prod"})//@Profile({"!prod && swagger"}) this is the best check for disabling
public class SwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      //to remove spring releated apis
      .apis(RequestHandlerSelectors.basePackage("com.example.SpringSecurityJWTDemo"))
      //can be configured to point only a type of API Paths
      .paths(PathSelectors.regex("/.*"))
      .build()
      .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo(){
    return new ApiInfo(
      "Spring Boot JWT security with Swagger UI",
      "Demo project for Spring Boot security with JWT and Swagger. Swagger to be disabled in production config.",
      "1.0",
      "Free to use",
      new Contact("Nitin Chaturvedi","https://www.linkedin.com/in/nitin-chaturvedi-b2348333","test@test.com"),
      "API Liscense",
      "https://www.linkedin.com/in/nitin-chaturvedi-b2348333",
      Collections.emptyList()
    );
  }

}

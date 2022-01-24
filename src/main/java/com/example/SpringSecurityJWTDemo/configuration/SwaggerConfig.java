package com.example.SpringSecurityJWTDemo.configuration;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"!prod"})//@Profile({"!prod && swagger"}) this is the best check for disabling
public class SwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
      //for setting custom info
      .apiInfo(getApiInfo())
      //for JWT token to added in header
      .securityContexts(Arrays.asList(securityContext()))
      .securitySchemes(Arrays.asList(apiKey()))
      // for configuring the API's and models we want to show
      // can be configured to point only to a package or a type of API Paths
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.example.SpringSecurityJWTDemo"))
      .paths(regex("/.*"))
      .build()
      ;
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

  private ApiKey apiKey() {
    return new ApiKey("JWT", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth())
      //enable only for certain paths
      .forPaths(regex("/hello/*"))
      .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
  }

}

package com.org.trustservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {


  /**
   * Environment details.
   */
  @Autowired
  private Environment env;

  /**
   * Will configure the allowed origins to registry.
   *
   * @param registry registry to have mapping configurations.
   */
  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins(env.getProperty("service.security.allowedOrigins"));
  }
}

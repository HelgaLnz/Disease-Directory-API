package com.example.diseaseapi;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
public class DiseaseConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors(AbstractHttpConfigurer::disable)
      .csrf(AbstractHttpConfigurer::disable)
      .build();
  }

}

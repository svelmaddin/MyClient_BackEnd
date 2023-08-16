package com.example.MyClientApp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public JwtAuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandler();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
//    @Bean
//    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
//        return new HttpSessionOAuth2AuthorizationRequestRepository();
//    }
//    @Bean
//    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
//        return new DefaultAuthorizationCodeTokenResponseClient();
//    }
}

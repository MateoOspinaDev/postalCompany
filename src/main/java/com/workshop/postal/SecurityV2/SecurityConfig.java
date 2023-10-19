package com.workshop.postal.SecurityV2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity>{

    private final ApiKeyAuthFilter apiKeyAuthFilter;
    private final String[] endpointsWithoutAuthentication = {"/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/api/clientes",
            "/v2/api-docs", "/configuration/ui",
            "/swagger-resources/**", "/configuration/security",
            "/swagger-ui.html", "/webjars/**", "/swagger-ui/**"
    } ;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .addFilterBefore(apiKeyAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .antMatchers(endpointsWithoutAuthentication).permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);


        return httpSecurity.build();
    }
}

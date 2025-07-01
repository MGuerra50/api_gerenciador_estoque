package com.inventory.manager.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

//    @Autowired
//    SecurityFi

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authHttpReq -> {
                            authHttpReq.requestMatchers(HttpMethod.POST, "/login").permitAll();
                            authHttpReq.requestMatchers(HttpMethod.POST, "/register").hasRole("ADMIN");
//                            authHttpReq.requestMatchers(new AntPathRequestMatcher("/register", "POST")).permitAll();
                            authHttpReq.anyRequest().authenticated()/*.and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)*/;
                        }
                )

                .build();
    }

}

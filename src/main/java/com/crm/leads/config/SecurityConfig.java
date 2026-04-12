package com.crm.leads.config;

import com.crm.leads.security.CustomReactiveAuthenticationManager;
import com.crm.leads.security.Exception.AuthenticationEntryPoint;
import com.crm.leads.security.Exception.ServerAccessDenied;
import com.crm.leads.security.JwtSecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    JwtSecurityContextRepository jwtSecurityContextRepository;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    ServerAccessDenied serverAccessDenied;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http,
            ReactiveAuthenticationManager reactiveAuthenticationManager
    ) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .exceptionHandling(exceptionHandlingSpec -> {
                    exceptionHandlingSpec.authenticationEntryPoint(authenticationEntryPoint);
                    exceptionHandlingSpec.accessDeniedHandler(serverAccessDenied);
                })
                .authorizeExchange(auth ->
                        auth
                                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .pathMatchers("/api/auth/**").permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .authenticationManager(reactiveAuthenticationManager)
                .securityContextRepository(jwtSecurityContextRepository)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}

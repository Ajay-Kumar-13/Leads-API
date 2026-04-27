package com.crm.leads.config;

import com.crm.leads.security.CustomReactiveAuthenticationManager;
import com.crm.leads.security.JwtSecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    private final ServerAuthenticationEntryPoint serverAuthenticationEntryPoint;
    private final ServerAccessDeniedHandler serverAccessDeniedHandler;
    private final JwtSecurityContextRepository jwtSecurityContextRepository;

    public SecurityConfig (
            ServerAuthenticationEntryPoint serverAuthenticationEntryPoint,
            ServerAccessDeniedHandler serverAccessDeniedHandler,
            JwtSecurityContextRepository jwtSecurityContextRepository
    ) {
        this.serverAuthenticationEntryPoint = serverAuthenticationEntryPoint;
        this.serverAccessDeniedHandler = serverAccessDeniedHandler;
        this.jwtSecurityContextRepository = jwtSecurityContextRepository;
    }
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http,
            CustomReactiveAuthenticationManager authenticationManager
    ) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .exceptionHandling(exceptionHandlingSpec ->
                    exceptionHandlingSpec.authenticationEntryPoint(serverAuthenticationEntryPoint)
                            .accessDeniedHandler(serverAccessDeniedHandler)
                )
                .authorizeExchange(auth ->
                        auth
                                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .pathMatchers("/api/auth/**").permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .authenticationManager(authenticationManager)
                .securityContextRepository(jwtSecurityContextRepository)
                .build();
    }
}

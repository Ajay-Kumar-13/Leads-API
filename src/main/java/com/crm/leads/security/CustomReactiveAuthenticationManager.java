package com.crm.leads.security;

import com.crm.leads.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomReactiveAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtUtils jwtUtils;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token= authentication.getCredentials().toString();
        return Mono.fromCallable(() -> jwtUtils.getClaims(token))
                .flatMap(claims -> {

                    String role = claims.get("roles").toString();
                    List<String> authorities = claims.get("authorities", List.class);
                    List<SimpleGrantedAuthority> finalAuthorities = new ArrayList<>();

                    if(role !=null) {
                        finalAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role));
                    }
                    if(authorities != null) {
                        finalAuthorities.addAll(authorities.stream().map(SimpleGrantedAuthority::new).toList());
                    }
                    return Mono.just(new UsernamePasswordAuthenticationToken(claims.getSubject(), null, finalAuthorities));
                });
    }
}

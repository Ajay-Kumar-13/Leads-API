package com.crm.leads.security.Exception;

import com.crm.leads.DTO.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Component
public class ServerAccessDenied implements ServerAccessDeniedHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(HttpStatus.FORBIDDEN.name())
                .message("Access Denied")
                .build();

        return Mono.fromCallable(() -> objectMapper.writeValueAsBytes(errorResponse))
                .flatMap(bytes -> {
                    DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(bytes);
                    return exchange.getResponse().writeWith(Mono.just(dataBuffer));
                });
    }
}

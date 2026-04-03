package com.crm.leads.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping("/test")
    public Mono<String>test() {
        return Mono.just("Hello world, from leads");
    }
}

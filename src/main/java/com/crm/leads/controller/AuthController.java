package com.crm.leads.controller;

import com.crm.leads.DTO.CreateLeadRequest;
import com.crm.leads.model.Lead;
import com.crm.leads.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final LeadRepository leadRepository;
    @GetMapping
    public String test() {
        return "Hello world!";
    }

    @PostMapping("/create-lead")
    public Mono<String> testDb(@RequestBody CreateLeadRequest createLeadRequest) {
        Lead lead = new Lead();
        lead.setAssociatedUserId(createLeadRequest.getAssociateUserId());
        return leadRepository.save(lead)
                .flatMap(l -> Mono.just("success"));
    }
}

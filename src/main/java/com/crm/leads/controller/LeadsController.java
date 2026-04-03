package com.crm.leads.controller;

import com.crm.leads.DTO.Lead;
import com.crm.leads.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leads")
public class LeadsController {

    private final LeadService leadService;

    @PostMapping
    public Mono<ResponseEntity<String>> uploadLeads(@RequestBody List<Lead> leads) {
        return leadService.uploadLeadsFromExcel(leads);
    }
}

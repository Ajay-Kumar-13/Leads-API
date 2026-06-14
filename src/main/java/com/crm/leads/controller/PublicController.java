package com.crm.leads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.leads.DTO.Lead;
import com.crm.leads.service.LeadService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/handle-leads/submit-lead")
public class PublicController {

    @Autowired
    private LeadService leadService;

    @PostMapping
    public Mono<ResponseEntity<String>> submitLead(@RequestBody Lead lead) {
        return leadService.directLeadsFromWebsite(lead);
    }
}
package com.crm.leads.controller;

import com.crm.leads.DTO.Lead;
import com.crm.leads.DTO.UpdateLead;
import com.crm.leads.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/leads")
public class LeadsController {

    private final LeadService leadService;

    @PostMapping
    public Mono<ResponseEntity<String>> uploadLeads(@RequestBody List<Lead> leads) {
        return leadService.uploadLeadsFromExcel(leads);
    }

    @GetMapping
    public Mono<List<Lead>> fetchLeads() {
        return leadService.getAllLeads();
    }

    @PutMapping
    public Mono<ResponseEntity<String>> assignLead(@RequestBody UpdateLead lead) {
        return leadService.assignLead(lead.getLeadId(), lead.getAssignedTo());
    }
}

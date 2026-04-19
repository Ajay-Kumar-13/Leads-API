package com.crm.leads.service;

import com.crm.leads.DTO.Lead;
import com.crm.leads.Exception.LeadsException;
import com.crm.leads.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepository leadRepository;

    public Mono<ResponseEntity<String>> uploadLeadsFromExcel(List<Lead> leads) {
        Flux<com.crm.leads.model.Lead> convertedLeads = Flux.fromIterable(leads)
                .map(dto -> new com.crm.leads.model.Lead(dto.getName(), dto.getPhone(), dto.getEmail(), dto.getAddress(), dto.getLeadSource(), dto.getLeadState(), dto.getLeadSubSource(), dto.getLeadType()));
        return leadRepository.saveAll(convertedLeads)
                .onErrorMap(e -> new LeadsException(HttpStatus.INTERNAL_SERVER_ERROR, "511", "Failed to upload leads"))
                .then(Mono.just(ResponseEntity.status(201).body("Successfully Uploaded all the Leads!")));

    }

    public Mono<List<Lead>> getAllLeads() {
        return leadRepository.findAll()
                .onErrorMap(e -> new LeadsException(HttpStatus.INTERNAL_SERVER_ERROR, "512", "Failed to fetch leads"))
//                .map(lead -> convertToDTO(lead))
                .map(this::convertToDTO)
                .collectList();
    }

    public Mono<ResponseEntity<String>> assignLead(UUID leadId, UUID assignedTo) {
        return leadRepository.findById(leadId)
                .onErrorMap(e -> new LeadsException(HttpStatus.INTERNAL_SERVER_ERROR, "513", "Failed to assign lead"))
                .flatMap(lead -> {
                    lead.setAssignedTo(assignedTo);
                    return leadRepository.save(lead);
                })
                .then(Mono.just(ResponseEntity.status(201).body("Updated Successfully")));
    }

    public Lead convertToDTO(com.crm.leads.model.Lead lead) {
        Lead l = new Lead();
        l.setId(lead.getId());
        l.setAssignedTo(lead.getAssignedTo());
        l.setName(lead.getName());
        l.setEmail(lead.getEmail());
        l.setAddress(lead.getAddress());
        l.setLeadSource(lead.getLeadSource());
        l.setLeadState(lead.getLeadState());
        l.setLeadSubSource(lead.getLeadSubSource());
        l.setPhone(lead.getPhone());
        l.setLeadType(lead.getLeadType());
        return l;
    }
}
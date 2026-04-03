package com.crm.leads.service;

import com.crm.leads.DTO.Lead;
import com.crm.leads.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepository leadRepository;

    public Mono<ResponseEntity<String>> uploadLeadsFromExcel(List<Lead> leads) {
        Flux<com.crm.leads.model.Lead> convertedLeads = Flux.fromIterable(leads)
                .map(dto -> new com.crm.leads.model.Lead(dto.getName(), dto.getPhone(), dto.getEmail(), dto.getAddress(), dto.getLeadSource(), dto.getLeadState(), dto.getLeadSubSource(), dto.getLeadType()));
        return leadRepository.saveAll(convertedLeads)
                .then(Mono.just(ResponseEntity.status(201).body("Successfully Uploaded all the Leads!")));
    }

}

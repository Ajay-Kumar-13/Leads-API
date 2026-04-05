package com.crm.leads.repository;

import com.crm.leads.model.Lead;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface LeadRepository extends R2dbcRepository<Lead, UUID> {
    Mono<Lead> findById(UUID id);
}

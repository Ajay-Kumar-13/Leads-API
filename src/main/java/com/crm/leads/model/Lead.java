package com.crm.leads.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table
@Data

public class Lead {
    @Id
    private UUID id;
    private String name;
    private Long phone;
    private String email;
    private String address;
    private String leadSource;
    private String leadState;
    private String leadSubSource;
    private String leadType;

    public Lead(String name, Long phone, String email, String address, String leadSource, String leadState, String leadSubSource, String leadType) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.leadSource = leadSource;
        this.leadState = leadState;
        this.leadSubSource = leadSubSource;
        this.leadType = leadType;
    }

    private UUID assignedTo;
}

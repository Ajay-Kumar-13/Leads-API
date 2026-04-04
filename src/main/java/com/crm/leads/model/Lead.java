package com.crm.leads.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("leads")
@Data

public class Lead {
    @Id
    private UUID id;
    private String name;
    private Long phone;
    private String email;
    private String address;

    @Column("lead_source")
    private String leadSource;

    @Column("lead_state")
    private String leadState;

    @Column("lead_sub_source")
    private String leadSubSource;

    @Column("lead_type")
    private String leadType;

    @Column("assigned_to")
    private UUID assignedTo;

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

}

package com.crm.leads.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateLead {
    private String name;
    private String phone;
    private String email;
    private UUID assignedTo;
    private UUID id;
    private String leadStatus;
}
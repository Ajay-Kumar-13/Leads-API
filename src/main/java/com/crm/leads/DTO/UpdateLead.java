package com.crm.leads.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateLead {
    private UUID assignedTo;
    private UUID leadId;
}

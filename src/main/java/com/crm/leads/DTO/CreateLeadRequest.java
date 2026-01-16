package com.crm.leads.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateLeadRequest {
    private UUID associateUserId;
}

package com.crm.leads.DTO;

import lombok.Data;

@Data
public class Lead {
    private String name;
    private Long phone;
    private String email;
    private String address;
    private String leadSource;
    private String leadState;
    private String leadSubSource;
    private String leadType;
}
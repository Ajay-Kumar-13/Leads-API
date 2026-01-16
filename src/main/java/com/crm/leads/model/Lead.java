package com.crm.leads.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("leads")
public class Lead {

    @Id
    @Column("id")
    private UUID id;

    @Column("associated_user_id")
    private UUID associatedUserId;
}

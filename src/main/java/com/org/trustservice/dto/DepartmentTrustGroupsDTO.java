package com.org.trustservice.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class DepartmentTrustGroupsDTO {

    private UUID id;
    private String title;
    private List<TrustGroupDTO> trustGroups;
}


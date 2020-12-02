package com.org.trustservice.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentTrustGroupsDTO {

  private Long id;
  private String title;
  private List<TrustGroupDTO> trustGroups;
}


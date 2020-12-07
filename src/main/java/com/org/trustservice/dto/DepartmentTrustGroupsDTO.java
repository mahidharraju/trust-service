package com.org.trustservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentTrustGroupsDTO {

  private Long id;
  private String title;
  private List<TrustGroupDTO> trustGroups;
}


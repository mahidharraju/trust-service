package com.org.trustservice.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TrustGroupDTO {

  private Long trustGroupId;
  private String name;
  private Map<String, Boolean> permissions;
}

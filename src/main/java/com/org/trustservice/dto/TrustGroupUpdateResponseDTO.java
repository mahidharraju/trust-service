package com.org.trustservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrustGroupUpdateResponseDTO {

  private Long id;
  private String title;
  private List<TrustGroupUpdateDTO> trustGroups;
}

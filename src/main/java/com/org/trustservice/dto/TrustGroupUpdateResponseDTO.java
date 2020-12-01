package com.org.trustservice.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrustGroupUpdateResponseDTO {

    private UUID id;
    private String title;
    List<TrustGroupUpdateDTO> trustGroups;
}

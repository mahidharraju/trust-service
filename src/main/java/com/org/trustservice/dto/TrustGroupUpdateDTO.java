package com.org.trustservice.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrustGroupUpdateDTO implements Comparable<TrustGroupUpdateDTO> {

    private UUID trustGroupId;
    private String name;
    private Boolean canViewSharedFilesAndFolders;
    private Boolean canCommentOnSharedFilesAndFolders;
    private Boolean canEditSharedDrivesAndFolders;
    private Boolean canTransferOwnership;


    @Override
    public int compareTo(TrustGroupUpdateDTO o) {
        return name.compareTo(o.getName());
    }
}

package com.org.trustservice.dto;

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
public class TrustGroupUpdateDTO implements Comparable<TrustGroupUpdateDTO> {

  private Long trustGroupId;
  private String name;
  private Boolean canViewSharedFilesAndFolders;
  private Boolean canCommentOnSharedFilesAndFolders;
  private Boolean canEditSharedDrivesAndFolders;
  private Boolean canTransferOwnership;


  @Override
  public int compareTo(final TrustGroupUpdateDTO o) {
    return name.compareTo(o.getName());
  }
}

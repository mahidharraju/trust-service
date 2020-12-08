package com.org.trustservice.model.id;

import java.io.Serializable;

public class TrustGroupPermissionId implements Serializable {

  private static final long serialVersionUID = 5873590346038679311L;
  private Long id;
  private Long tgFlavourId;
  private Long trustGroupId;

  public TrustGroupPermissionId() {
  }

  public TrustGroupPermissionId(final Long id, final Long tgFlavourId, final Long trustGroupId) {
    super();
    this.id = id;
    this.tgFlavourId = tgFlavourId;
    this.trustGroupId = trustGroupId;
  }

  public Long getId() {
    return id;
  }

  public Long getTgFlavourId() {
    return tgFlavourId;
  }

  public Long getTrustGroupId() {
    return trustGroupId;
  }

  public boolean equals(final Object o) {
    return ((o instanceof TrustGroupPermissionId)
        && id == ((TrustGroupPermissionId) o).getId()
        && tgFlavourId == ((TrustGroupPermissionId) o).getTgFlavourId()
        && trustGroupId == ((TrustGroupPermissionId) o).getTrustGroupId());
  }

  @Override
  public int hashCode() {
    return id.hashCode() + tgFlavourId.hashCode() + trustGroupId.hashCode();
  }
}

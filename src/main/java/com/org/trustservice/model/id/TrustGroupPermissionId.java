package com.org.trustservice.model.id;

import java.io.Serializable;
import java.util.UUID;

public class TrustGroupPermissionId implements Serializable {

  private static final long serialVersionUID = 5873590346038679311L;
  private UUID id;
  private UUID tgFlavourId;
  private UUID trustGroupId;

  public TrustGroupPermissionId() {
  }

  public TrustGroupPermissionId(final UUID id, final UUID tgFlavourId, final UUID trustGroupId) {
    super();
    this.id = id;
    this.tgFlavourId = tgFlavourId;
    this.trustGroupId = trustGroupId;
  }

  public UUID getId() {
    return id;
  }

  public UUID getTgFlavourId() {
    return tgFlavourId;
  }

  public UUID getTrustGroupId() {
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

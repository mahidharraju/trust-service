package com.org.trustservice.model.id;

import java.io.Serializable;

public class DepartmentTrustGroupID implements Serializable {

  private static final long serialVersionUID = -7361340574065718216L;
  private Long id;
  private Long deptId;
  private Long tgFlavourId;
  private Long orgCollabId;

  public DepartmentTrustGroupID() {
    super();
  }

  public DepartmentTrustGroupID(final Long id, final Long deptId, final Long tgFlavourId, final Long orgCollabId) {
    super();
    this.id = id;
    this.tgFlavourId = tgFlavourId;
    this.deptId = deptId;
    this.orgCollabId = orgCollabId;
  }

  public Long getId() {
    return id;
  }


  public Long getDeptId() {
    return deptId;
  }

  public Long getTgFlavourId() {
    return tgFlavourId;
  }

  public Long getOrgCollabId() {
    return orgCollabId;
  }

  public boolean equals(final Object o) {
    return ((o instanceof TrustGroupPermissionId)
        && id == ((DepartmentTrustGroupID) o).getId()
        && deptId == ((DepartmentTrustGroupID) o).getDeptId()
        && tgFlavourId == ((DepartmentTrustGroupID) o).getTgFlavourId()
        && orgCollabId == ((DepartmentTrustGroupID) o).getOrgCollabId());
  }

  @Override
  public int hashCode() {
    return id.hashCode() + deptId.hashCode() + tgFlavourId.hashCode() + orgCollabId.hashCode();
  }
}

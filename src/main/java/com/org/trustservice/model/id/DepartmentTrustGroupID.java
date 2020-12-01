package com.org.trustservice.model.id;

import java.io.Serializable;
import java.util.UUID;

public class DepartmentTrustGroupID implements Serializable {

    private static final long serialVersionUID = -7361340574065718216L;
    private UUID id;
    private UUID deptId;
    private UUID tgFlavourId;
    private UUID orgCollabId;

    public DepartmentTrustGroupID() {
        super();
    }

    public DepartmentTrustGroupID(UUID id, UUID deptId, UUID tgFlavourId, UUID orgCollabId) {
        super();
        this.id = id;
        this.tgFlavourId = tgFlavourId;
        this.deptId = deptId;
        this.orgCollabId = orgCollabId;
    }

    public UUID getId() {
        return id;
    }


    public UUID getDeptId() {
        return deptId;
    }

    public UUID getTgFlavourId() {
        return tgFlavourId;
    }

    public UUID getOrgCollabId() {
        return orgCollabId;
    }

    public boolean equals(Object o) {
        return ((o instanceof TrustGroupPermissionId) &&
                id == ((DepartmentTrustGroupID) o).getId() &&
                deptId == ((DepartmentTrustGroupID) o).getDeptId() &&
                tgFlavourId == ((DepartmentTrustGroupID) o).getTgFlavourId() &&
                orgCollabId == ((DepartmentTrustGroupID) o).getOrgCollabId());
    }
}

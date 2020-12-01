package com.org.trustservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.org.trustservice.model.TrustGroupPermission;
import com.org.trustservice.model.id.TrustGroupPermissionId;

public interface TrustGroupPermissionRepository extends JpaRepository<TrustGroupPermission, TrustGroupPermissionId> {


}

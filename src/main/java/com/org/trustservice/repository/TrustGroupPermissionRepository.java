package com.org.trustservice.repository;

import com.org.trustservice.model.TrustGroupPermission;
import com.org.trustservice.model.id.TrustGroupPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TrustGroupPermissionRepository
    extends JpaRepository<TrustGroupPermission, TrustGroupPermissionId> {
}

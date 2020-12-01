package com.org.trustservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.trustservice.model.DepartmentTrustGroup;

public interface DepartmentTrustGroupRepository extends JpaRepository<DepartmentTrustGroup, UUID> {

    List<DepartmentTrustGroup> findByDeptIdAndOrgCollabId(UUID deptId, UUID orgCollabId);
}

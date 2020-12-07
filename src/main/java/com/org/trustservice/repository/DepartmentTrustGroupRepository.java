package com.org.trustservice.repository;

import com.org.trustservice.model.DepartmentTrustGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentTrustGroupRepository extends JpaRepository<DepartmentTrustGroup, Long> {

  List<DepartmentTrustGroup> findByDeptIdAndOrgCollabId(Long deptId, Long orgCollabId);
}

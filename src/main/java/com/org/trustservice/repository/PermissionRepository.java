package com.org.trustservice.repository;

import com.org.trustservice.model.Permission;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

  Optional<Permission> findByNameAndPermissionValue(String name, Boolean value);
}

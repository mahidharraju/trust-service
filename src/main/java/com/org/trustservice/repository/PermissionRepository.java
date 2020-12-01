package com.org.trustservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.org.trustservice.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {

    public Permission findByNameAndPermissionValue(String name, Boolean value);
}

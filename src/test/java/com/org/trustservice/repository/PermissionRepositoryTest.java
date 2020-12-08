package com.org.trustservice.repository;


import com.org.trustservice.model.Permission;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PermissionRepositoryTest {


  @Autowired
  private PermissionRepository permissionRepository;

  @Test
  void testPermissionRepositorySave() {
    Permission permission = Permission
        .builder()
        .permissionValue(false)
        .description("test")
        .name("test")
        .createdBy("ADMIN")
        .createdDate(LocalDateTime.now())
        .build();
    permissionRepository.save(permission);
    List<Permission> permissions = permissionRepository.findAll();
    Assertions.assertEquals(9, permissions.size());
  }
}

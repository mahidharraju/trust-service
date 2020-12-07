package com.org.trustservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.trustservice.model.Department;
import com.org.trustservice.model.DepartmentTrustGroup;
import com.org.trustservice.model.Permission;
import com.org.trustservice.model.TrustGroup;
import com.org.trustservice.model.TrustGroupFlavour;
import com.org.trustservice.model.TrustGroupPermission;
import com.org.trustservice.repository.DepartmentRepository;
import com.org.trustservice.repository.DepartmentTrustGroupRepository;
import com.org.trustservice.repository.PermissionRepository;
import com.org.trustservice.repository.TrustGroupFlavourRepository;
import com.org.trustservice.repository.TrustGroupPermissionRepository;
import com.org.trustservice.repository.TrustGroupRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class TrustServiceIntegrationTest {


  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private PermissionRepository permissionRepository;

  @Autowired
  TrustGroupFlavourRepository trustGroupFlavourRepository;

  @Autowired
  TrustGroupRepository trustGroupRepository;

  @Autowired
  TrustGroupPermissionRepository trustGroupPermissionRepository;

  @Autowired
  DepartmentRepository departmentRepository;

  @Autowired
  DepartmentTrustGroupRepository departmentTrustGroupRepository;

  @Test
  public void saveAndFetchTrustGroupTest(){
    List<Permission> permissions = permissionRepository.findAll();
    permissions.forEach(permission -> System.out.println(permission.getId()));
    Optional<TrustGroupFlavour> trustGroupFlavour = trustGroupFlavourRepository.findById(1L);
    System.out.println(trustGroupFlavour.get());
    Optional<TrustGroup> trsutGroup = trustGroupRepository.findById(1L);
    Optional<Department> department = departmentRepository.findById(1L);
    System.out.println(trsutGroup.get().getId());
    TrustGroupPermission tgPermission = new TrustGroupPermission();
    tgPermission.setFlavour(trustGroupFlavour.get());
    tgPermission.setCreatedBy("Test");
    tgPermission.setCreatedDate(LocalDateTime.now());
    tgPermission.setTrustGroup(trsutGroup.get());
    tgPermission.setPermission(permissions.get(0));

    //trustGroupPermissionRepository.save(tgPermission);
    /*DepartmentTrustGroup deptTrustGroup = DepartmentTrustGroup
        .builder()
        .dept(department.get())
        .flavour(trustGroupFlavour.get())
        .build();
    departmentTrustGroupRepository.save(deptTrustGroup);
    List<DepartmentTrustGroup> deptTG = departmentTrustGroupRepository.findAll();

    deptTG.forEach(dtg-> System.out.println(dtg.getId()));
*/



  }
}

package com.org.trustservice.service;

import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.exception.NoDataFoundException;
import com.org.trustservice.model.Department;
import com.org.trustservice.model.DepartmentTrustGroup;
import com.org.trustservice.model.GooglePermissions;
import com.org.trustservice.model.GoogleRoles;
import com.org.trustservice.model.Permission;
import com.org.trustservice.model.TrustGroup;
import com.org.trustservice.model.TrustGroupFlavour;
import com.org.trustservice.model.TrustGroupPermission;
import com.org.trustservice.repository.DepartmentTrustGroupRepository;
import com.org.trustservice.repository.PermissionRepository;
import com.org.trustservice.repository.TrustGroupFlavourRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class TrustGroupServiceTest {

  @Mock
  private DepartmentTrustGroupRepository departmentTGRepository;

  @Mock
  private TrustGroupFlavourRepository trustGroupFlavourRepository;

  @Mock
  private PermissionRepository permissionRepository;

  @Mock
  private GooglePermissions googlePermissions;

  @Mock
  private GoogleRoles googleRoles;

  @InjectMocks
  private TrustGroupService trustGroupService;

  private List<TrustGroupPermission> permissions = new ArrayList<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testExceptionWhenNoDataFromGetAllTrustGroupsByDepartment() {
    Long orgCollabId = 1234L;
    Long departmentId = 123L;
    List<DepartmentTrustGroup> deptTrustGroups = new ArrayList<>();
    deptTrustGroups.add(DepartmentTrustGroup
        .builder()
        .id(123L)
        .deptId(123L)
        .orgCollabId(1234L)
        .createdBy("ADMIN")
        .build());
    Mockito.when(departmentTGRepository.findByDeptIdAndOrgCollabId(departmentId, orgCollabId)).thenReturn(deptTrustGroups);
    Assertions.assertThrows(NoDataFoundException.class, () -> trustGroupService.getAllTrustGroupsByDepartment(orgCollabId, departmentId));
  }


  @Test
  void testGetAllTrustGroupsByDepartment() {
    Long orgCollabId = 1234L;
    Long departmentId = 123L;
    List<DepartmentTrustGroup> deptTrustGroups = new ArrayList<>();
    deptTrustGroups = new ArrayList<>();
    permissions = new ArrayList<>();
    permissions.add(TrustGroupPermission
        .builder()
        .trustGroup(TrustGroup
            .builder()
            .name("DO NOT TRUST")
            .build())
        .permission(Permission
            .builder()
            .name("Can edit and invite collaborators")
            .permissionValue(false)
            .build())
        .build());
    deptTrustGroups.add(DepartmentTrustGroup
        .builder()
        .id(123L)
        .dept(Department
            .builder()
            .id(123L)
            .name("Sales")
            .build())
        .flavour(TrustGroupFlavour
            .builder()
            .trustGroupPermissions(permissions)
            .build())
        .orgCollabId(1234L)
        .createdBy("ADMIN")
        .build());
    Mockito.when(departmentTGRepository.findByDeptIdAndOrgCollabId(departmentId, orgCollabId)).thenReturn(deptTrustGroups);
    TrustGroupUpdateResponseDTO response = trustGroupService.getAllTrustGroupsByDepartment(orgCollabId, departmentId);
    Assertions.assertNotNull(response, "success");
  }

  @Test
  void testUpdateTrustGroups() {
    TrustGroupUpdateDTO requestDTO = TrustGroupUpdateDTO
        .builder()
        .trustGroupId(123L)
        .canCommentOnSharedFilesAndFolders(false)
        .canEditSharedDrivesAndFolders(false)
        .canViewSharedFilesAndFolders(true)
        .canTransferOwnership(false)
        .name("TOP 30000")
        .build();
    permissions = new ArrayList<>();
    TrustGroupPermission permission1 = TrustGroupPermission
        .builder()
        .tgFlavourId(1L)
        .trustGroup(TrustGroup
            .builder()
            .name("TOP 100")
            .id(1L)
            .build())
        .permission(Permission
            .builder()
            .name("Can view shared files and folders?")
            .permissionValue(false)
            .build())
        .build();
    TrustGroupPermission permission2 = TrustGroupPermission
        .builder()
        .tgFlavourId(1L)
        .trustGroup(TrustGroup
            .builder()
            .name("TOP 100")
            .id(1L)
            .build())
        .permission(Permission
            .builder()
            .name("Can edit shared drives and folders?")
            .permissionValue(true)
            .build())
        .build();
    permissions.add(permission1);
    permissions.add(permission2);
    TrustGroupFlavour currentFlavour = TrustGroupFlavour
        .builder()
        .id(1L)
        .trustGroupPermissions(permissions)
        .build();
    Permission p1 = Permission
        .builder()
        .name("Can edit shared drives and folders?")
        .permissionValue(true)
        .build();
    Mockito.when(googlePermissions.getCommenter()).thenReturn("Can comment on shared files and folders?");
    Mockito.when(googlePermissions.getOwner()).thenReturn("Can transfer ownership?");
    Mockito.when(googlePermissions.getReader()).thenReturn("Can view shared files and folders?");
    Mockito.when(googlePermissions.getWriter()).thenReturn("Can edit shared drives and folders?");
    Mockito.when(trustGroupFlavourRepository.findById(requestDTO.getTrustGroupId())).thenReturn(Optional.of(currentFlavour));
    Mockito.when(permissionRepository.findByNameAndPermissionValue(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(Optional.of(p1));
    Mockito.when(trustGroupFlavourRepository.save(currentFlavour)).thenReturn(currentFlavour);
    Assertions.assertDoesNotThrow(() -> trustGroupService.updateTrustGroups(requestDTO));
  }

  @Test
  void testGetRoleBasedOnFlavour() {
    TrustGroupFlavour flavour = TrustGroupFlavour
        .builder()
        .flavourDescription("Top 100")
        .role("owner")
        .build();
    Mockito.when(trustGroupFlavourRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(flavour));
    String role = trustGroupService.getRoleBasedOnFlavour(123L);
    Assertions.assertSame("owner",role);
  }
}

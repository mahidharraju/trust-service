package com.org.trustservice.service;

import com.org.trustservice.InputDataCreator;
import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.exception.NoDataFoundException;
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


  TrustGroupUpdateDTO requestDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    requestDTO = TrustGroupUpdateDTO
        .builder()
        .trustGroupId(123L)
        .canCommentOnSharedFilesAndFolders(false)
        .canEditSharedDrivesAndFolders(false)
        .canViewSharedFilesAndFolders(true)
        .canTransferOwnership(false)
        .name("TOP 30000")
        .build();
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
    List<DepartmentTrustGroup> trustGroupsOfDepartment = InputDataCreator.getDepartmentTrustGroupData();
    Long orgCollabId = 1234L;
    Long departmentId = 1L;
    Mockito.when(departmentTGRepository.findByDeptIdAndOrgCollabId(departmentId, orgCollabId)).thenReturn(trustGroupsOfDepartment);
    TrustGroupUpdateResponseDTO response = trustGroupService.getAllTrustGroupsByDepartment(orgCollabId, departmentId);
    Assertions.assertNotNull(response, "success");
  }


  @Test
  void testUpdateTrustGroups() {
    List<TrustGroupPermission> permissions = new ArrayList<>();
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
            .permissionValue(true)
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
    Permission nonEditorPermission = Permission
        .builder()
        .name("Can edit shared drives and folders?")
        .permissionValue(false)
        .build();
    Mockito.when(googlePermissions.getCommenter()).thenReturn("Can comment on shared files and folders?");
    Mockito.when(googlePermissions.getOwner()).thenReturn("Can transfer ownership?");
    Mockito.when(googlePermissions.getReader()).thenReturn("Can view shared files and folders?");
    Mockito.when(googlePermissions.getWriter()).thenReturn("Can edit shared drives and folders?");
    Mockito.when(googleRoles.getOwner()).thenReturn("owner");
    Mockito.when(googleRoles.getCommenter()).thenReturn("commenter");
    Mockito.when(googleRoles.getReader()).thenReturn("reader");
    Mockito.when(googleRoles.getWriter()).thenReturn("writer");
    Mockito.when(trustGroupFlavourRepository.findById(requestDTO.getTrustGroupId())).thenReturn(Optional.of(currentFlavour));
    Mockito.when(permissionRepository.findByNameAndPermissionValue("Can edit shared drives and folders?", false)).thenReturn(Optional.of(nonEditorPermission));
    Mockito.when(trustGroupFlavourRepository.save(currentFlavour)).thenReturn(currentFlavour);
    TrustGroupFlavour response = trustGroupService.updateTrustGroups(requestDTO);
    Assertions.assertEquals("reader", response.getRole());
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
    Assertions.assertSame("owner", role);
  }

  @Test
  void testNoDataFoundExceptionWithUpdateTrustGroups() {
    Mockito.when(trustGroupFlavourRepository.findById(requestDTO.getTrustGroupId())).thenReturn(Optional.empty());
    Assertions.assertThrows(NoDataFoundException.class, () -> trustGroupService.updateTrustGroups(requestDTO));
  }
}

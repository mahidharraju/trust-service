package com.org.trustservice.service;

import com.org.trustservice.dto.DepartmentTrustGroupsDTO;
import com.org.trustservice.dto.TrustGroupDTO;
import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.excpetion.NoDataFoundException;
import com.org.trustservice.excpetion.ResourceNotFoundException;
import com.org.trustservice.model.DepartmentTrustGroup;
import com.org.trustservice.model.GooglePermissions;
import com.org.trustservice.model.GoogleRoles;
import com.org.trustservice.model.TrustGroupFlavour;
import com.org.trustservice.model.TrustGroupPermission;
import com.org.trustservice.repository.DepartmentTrustGroupRepository;
import com.org.trustservice.repository.PermissionRepository;
import com.org.trustservice.repository.TrustGroupFlavourRepository;
import com.org.trustservice.util.TrustGroupNameComparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Service
public class TrustGroupService implements ITrustGroupService {


  private final DepartmentTrustGroupRepository departmentTGRepository;

  private final TrustGroupFlavourRepository trustGroupFlavourRepository;

  private final PermissionRepository permissionRepository;

  private final GooglePermissions googlePermissions;

  private final GoogleRoles googleRoles;

  public TrustGroupService(final DepartmentTrustGroupRepository departmentTGRepository,
                           final TrustGroupFlavourRepository trustGroupFlavourRepository,
                           final PermissionRepository permissionRepository,
                           final GooglePermissions googlePermissions,
                           final GoogleRoles googleRoles) {
    this.departmentTGRepository = departmentTGRepository;
    this.trustGroupFlavourRepository = trustGroupFlavourRepository;
    this.permissionRepository = permissionRepository;
    this.googlePermissions = googlePermissions;
    this.googleRoles = googleRoles;
  }

  /**
   * This method will read data from database based on input data and form a response for UI.
   *
   * @param orgCollabId
   * @param departmentId
   * @return returns TrustGroupUpdateResponseDTO  suitable for UI
   */

  public TrustGroupUpdateResponseDTO getAllTrustGroupsByDepartment(
      final Long orgCollabId,
      final Long departmentId) {
    List<DepartmentTrustGroup> departmentTrustGroups =
        departmentTGRepository.findByDeptIdAndOrgCollabId(
            departmentId,
            orgCollabId);
    if (!CollectionUtils.isEmpty(departmentTrustGroups) && departmentTrustGroups.get(0).getDept() != null) {
      DepartmentTrustGroupsDTO responseDto = buildResponseDTO(departmentTrustGroups);
      return convertResponseDTOToUICompatibleResponse(responseDto);
    } else {
      throw new NoDataFoundException("Unable to get Trust group Info with this ID" + departmentId);
    }
  }

  /**
   * @param responseDTO
   * @return
   */
  private TrustGroupUpdateResponseDTO convertResponseDTOToUICompatibleResponse(
      final DepartmentTrustGroupsDTO responseDTO) {
    TrustGroupUpdateResponseDTO apiResponse = new TrustGroupUpdateResponseDTO();
    List<TrustGroupUpdateDTO> trustGroupsList = responseDTO.getTrustGroups().stream().map(trustGroup -> TrustGroupUpdateDTO
        .builder()
        .trustGroupId(trustGroup.getTrustGroupId())
        .name(trustGroup.getName())
        .canViewSharedFilesAndFolders(trustGroup.getPermissions().get(googlePermissions.getReader()))
        .canCommentOnSharedFilesAndFolders(trustGroup.getPermissions().get(googlePermissions.getCommenter()))
        .canEditSharedDrivesAndFolders(trustGroup.getPermissions().get(googlePermissions.getWriter()))
        .canTransferOwnership(trustGroup.getPermissions().get(googlePermissions.getOwner()))
        .build())
        .collect(Collectors.toList());
    trustGroupsList.sort(new TrustGroupNameComparator());
    apiResponse.setTrustGroups(trustGroupsList);
    apiResponse.setId(responseDTO.getId());
    apiResponse.setTitle(responseDTO.getTitle());
    return apiResponse;
  }

  /**
   * @param departmentTrustGroups
   * @return
   */
  private DepartmentTrustGroupsDTO buildResponseDTO(
      final List<DepartmentTrustGroup> departmentTrustGroups) {
    DepartmentTrustGroupsDTO responseDTO = new DepartmentTrustGroupsDTO();
    List<TrustGroupDTO> trustGroups = departmentTrustGroups.stream().map(TrustGroupService::buildEachTrustGroup).collect(Collectors.toList());
    responseDTO.setTrustGroups(trustGroups);
    responseDTO.setId(departmentTrustGroups.get(0).getDept().getId());
    responseDTO.setTitle(departmentTrustGroups.get(0).getDept().getName());
    return responseDTO;
  }

  private static TrustGroupDTO buildEachTrustGroup(DepartmentTrustGroup departmentTrustGroup) {
    TrustGroupFlavour flavour = departmentTrustGroup.getFlavour();
    if (flavour != null && !CollectionUtils.isEmpty(flavour.getTrustGroupPermissions())) {
      return TrustGroupDTO
          .builder()
          .trustGroupId(departmentTrustGroup.getTgFlavourId())
          .name(flavour.getTrustGroupPermissions().get(0).getTrustGroup().getName())
          .permissions(getPermissionMap(flavour.getTrustGroupPermissions()))
          .build();
    } else {
      throw new NoDataFoundException("No Trust groups associated to department : " + departmentTrustGroup.getDeptId());
    }
  }

  /**
   * @param trustGroupPermissions
   * @return
   */
  private static Map<String, Boolean> getPermissionMap(
      final List<TrustGroupPermission> trustGroupPermissions) {
    Map<String, Boolean> permissionMap = new HashMap<>();
    for (TrustGroupPermission permission : trustGroupPermissions) {
      permissionMap.put(
          permission.getPermission().getName(),
          permission.getPermission().getPermissionValue());
    }
    return permissionMap;
  }

  /**
   * @param requestDTO
   */
  public void updateTrustGroups(final TrustGroupUpdateDTO requestDTO) {
    Map<String, Boolean> permissionMap = populatePermissionMap(requestDTO);
    TrustGroupFlavour currentFlavour = trustGroupFlavourRepository.findById(
        requestDTO.getTrustGroupId()).orElseThrow(() -> new ResourceNotFoundException("No TrustGroup With given ID"));
    List<TrustGroupPermission> permissionList = currentFlavour.getTrustGroupPermissions();
    permissionList.forEach(
        tgPermission -> {
          String permissionName = tgPermission.getPermission().getName();
          if (permissionMap.get(permissionName) != null
              && !permissionMap.get(permissionName).equals(tgPermission.getPermission().getPermissionValue())) {
            tgPermission.setPermission(
                permissionRepository.findByNameAndPermissionValue(
                    tgPermission.getPermission().getName(),
                    permissionMap.get(permissionName)).
                    orElseThrow(() -> new ResourceNotFoundException("Unable to update trust group because " +
                        "invalid request data")));
          }
        }
    );
    if (Boolean.TRUE.equals(requestDTO.getCanTransferOwnership())) {
      currentFlavour.setRole(googleRoles.getOwner());
    } else if (Boolean.TRUE.equals(requestDTO.getCanEditSharedDrivesAndFolders())) {
      currentFlavour.setRole(googleRoles.getWriter());
    } else if (Boolean.TRUE.equals(requestDTO.getCanCommentOnSharedFilesAndFolders())) {
      currentFlavour.setRole(googleRoles.getCommenter());
    } else if (Boolean.TRUE.equals(requestDTO.getCanViewSharedFilesAndFolders())) {
      currentFlavour.setRole(googleRoles.getReader());
    } else {
      currentFlavour.setRole(googleRoles.getUnknown());
    }
    trustGroupFlavourRepository.save(currentFlavour);
  }

  /**
   * @param requestDTO
   * @return
   */
  private Map<String, Boolean> populatePermissionMap(
      final TrustGroupUpdateDTO requestDTO) {
    Map<String, Boolean> permissionMap = new HashMap<>();
    permissionMap.put(googlePermissions.getReader(), requestDTO.getCanViewSharedFilesAndFolders());
    permissionMap.put(googlePermissions.getCommenter(), requestDTO.getCanCommentOnSharedFilesAndFolders());
    permissionMap.put(googlePermissions.getWriter(), requestDTO.getCanEditSharedDrivesAndFolders());
    permissionMap.put(googlePermissions.getOwner(), requestDTO.getCanTransferOwnership());
    return permissionMap;
  }

  /**
   * @param trustGroupId
   * @return
   */
  public String getRoleBasedOnFlavour(
      final Long trustGroupId) {
    return trustGroupFlavourRepository
        .findById(trustGroupId)
        .map(TrustGroupFlavour::getRole).orElse(googleRoles.getUnknown());
  }
}

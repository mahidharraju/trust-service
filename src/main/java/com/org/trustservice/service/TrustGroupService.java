package com.org.trustservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.org.trustservice.excpetion.GenericAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.org.trustservice.dto.DepartmentTrustGroupsDTO;
import com.org.trustservice.dto.TrustGroupDTO;
import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.excpetion.DatabaseException;
import com.org.trustservice.model.DepartmentTrustGroup;
import com.org.trustservice.model.TrustGroupFlavour;
import com.org.trustservice.model.TrustGroupPermission;
import com.org.trustservice.repository.DepartmentTrustGroupRepository;
import com.org.trustservice.repository.PermissionRepository;
import com.org.trustservice.repository.TrustGroupFlavourRepository;
import com.org.trustservice.util.TrustGroupNameComparator;


/**
 * @author
 */
@Service
public class TrustGroupService {


    @Autowired
    DepartmentTrustGroupRepository departmentTGRepository;

    @Autowired
    TrustGroupFlavourRepository trustGroupFlavourRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Value("${google.permissions.1}")
    private static String permission1;

    @Value("${google.permissions.2}")
    private static String permission2;

    @Value("${google.permissions.3}")
    private static String permission3;

    @Value("${google.permissions.4}")
    private static String permission4;


    @Value("${google.role.writer}")
    private static String writer;

    @Value("${google.role.reader}")
    private static String reader;

    @Value("${google.role.commenter}")
    private static String commenter;

    @Value("${google.role.owner}")
    private static String owner;


    /**
     * This method will read data from database based on input data and form a response for UI
     *
     * @param orgCollabId
     * @param departmentId
     * @return returns TrustGroupUpdateResponseDTO  suitable for UI
     */
    public TrustGroupUpdateResponseDTO getAllTrustGroupsByDepartment(UUID orgCollabId, UUID departmentId) {
        TrustGroupUpdateResponseDTO apiResponse;
        try {
            List<DepartmentTrustGroup> departmentTrustGroups =
                    departmentTGRepository.findByDeptIdAndOrgCollabId(
                            departmentId,
                            orgCollabId);
            if (!departmentTrustGroups.isEmpty() && departmentTrustGroups.get(0).getDept() != null) {
                DepartmentTrustGroupsDTO responseDTO = buildResponseDTO(departmentTrustGroups);
                apiResponse = convertResponseDTOToUICompatibleResponse(responseDTO);
            } else throw new DatabaseException("Unable to get Trust group Info with this ID" + departmentId);
        } catch (Exception e) {
            throw new DatabaseException("Unable to get Trust group Info with this ID" + departmentId, e);
        }
        return apiResponse;
    }

    /**
     * @param responseDTO
     * @return
     */
    private TrustGroupUpdateResponseDTO convertResponseDTOToUICompatibleResponse(DepartmentTrustGroupsDTO responseDTO) {
        TrustGroupUpdateResponseDTO apiResponse = new TrustGroupUpdateResponseDTO();
        List<TrustGroupUpdateDTO> trustGroupsList = new ArrayList<>();
        for (TrustGroupDTO dto : responseDTO.getTrustGroups()) {
            TrustGroupUpdateDTO trustGroup = TrustGroupUpdateDTO
                    .builder()
                    .trustGroupId(dto.getTrustGroupId())
                    .name(dto.getName())
                    .canViewSharedFilesAndFolders(dto.getPermissions().get(permission1))
                    .canCommentOnSharedFilesAndFolders(dto.getPermissions().get(permission2))
                    .canEditSharedDrivesAndFolders(dto.getPermissions().get(permission3))
                    .canTransferOwnership(dto.getPermissions().get(permission4))
                    .build();
            trustGroupsList.add(trustGroup);
        }
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
    private DepartmentTrustGroupsDTO buildResponseDTO(List<DepartmentTrustGroup> departmentTrustGroups) {
        DepartmentTrustGroupsDTO responseDTO = new DepartmentTrustGroupsDTO();
        List<TrustGroupDTO> trustGroups = departmentTrustGroups.stream().map(deptTg ->
                TrustGroupDTO
                        .builder()
                        .trustGroupId(deptTg.getTgFlavourId())
                        .name(deptTg.getFlavour().getTrustGroupPermissions().get(0).getTrustGroup().getName())
                        .permissions(getPermissionMap(deptTg.getFlavour().getTrustGroupPermissions()))
                        .build()
        ).collect(Collectors.toList());
        responseDTO.setTrustGroups(trustGroups);
        responseDTO.setId(departmentTrustGroups.get(0).getDeptId());
        responseDTO.setTitle(departmentTrustGroups.get(0).getDept().getName());
        return responseDTO;
    }


    /**
     * @param trustGroupPermissions
     * @return
     */
    private Map<String, Boolean> getPermissionMap(List<TrustGroupPermission> trustGroupPermissions) {
        Map<String, Boolean> permissionMap = new HashMap<>();
        for (TrustGroupPermission permission : trustGroupPermissions) {
            permissionMap.put(permission.getPermission().getName(), permission.getPermission().getPermissionValue());
        }
        return permissionMap;
    }

    /**
     * @param requestDTO
     */
    public void updateTrustGroups(TrustGroupUpdateDTO requestDTO) {
        final Map<String, Boolean> permissionMap = populatePermissionMap(requestDTO);
        Optional<TrustGroupFlavour> currentFlavour = trustGroupFlavourRepository.findById(requestDTO.getTrustGroupId());
        List<TrustGroupPermission> permissionList = currentFlavour.map(
                flavour -> flavour.getTrustGroupPermissions())
                .orElseThrow(() -> new GenericAPIException("No Trust group matching ID:" + requestDTO.getTrustGroupId()));
        permissionList.forEach(
                tgPermission -> {
                    String permissionName = tgPermission.getPermission().getName();
                    if (permissionMap.get(permissionName) != null
                            && permissionMap.get(permissionName) != tgPermission.getPermission().getPermissionValue()) {
                        tgPermission.setPermission(
                                permissionRepository.findByNameAndPermissionValue(
                                        tgPermission.getPermission().getName(),
                                        permissionMap.get(tgPermission.getPermission().getName())));
                    }
                }
        );
        if (requestDTO.getCanTransferOwnership())
            currentFlavour.get().setRole(owner);
        else if (requestDTO.getCanEditSharedDrivesAndFolders())
            currentFlavour.get().setRole(writer);
        else if (requestDTO.getCanCommentOnSharedFilesAndFolders())
            currentFlavour.get().setRole(commenter);
        else if (requestDTO.getCanViewSharedFilesAndFolders())
            currentFlavour.get().setRole(reader);
        else currentFlavour.get().setRole("null");
        trustGroupFlavourRepository.save(currentFlavour.get());
    }

    /**
     * @param requestDTO
     * @return
     */
    private Map<String, Boolean> populatePermissionMap(TrustGroupUpdateDTO requestDTO) {
        Map<String, Boolean> permissionMap = new HashMap<>();
        permissionMap.put(permission1, requestDTO.getCanViewSharedFilesAndFolders());
        permissionMap.put(permission2, requestDTO.getCanCommentOnSharedFilesAndFolders());
        permissionMap.put(permission3, requestDTO.getCanEditSharedDrivesAndFolders());
        permissionMap.put(permission4, requestDTO.getCanTransferOwnership());
        return permissionMap;
    }

    /**
     * @param trustGroupId
     * @return
     */
    public String getRoleBasedOnFlavour(UUID trustGroupId) {
        return trustGroupFlavourRepository.findById(trustGroupId).get().getRole();
    }
}

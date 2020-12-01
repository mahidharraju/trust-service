package com.org.trustservice.controller;

import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.excpetion.GenericAPIException;
import com.org.trustservice.service.TrustGroupService;
import com.org.trustservice.util.Constants;
import com.org.trustservice.util.ControllerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TrustGroupPermissionController {

    @Autowired
    TrustGroupService trustGroupService;


    @GetMapping("/departments/{departmentId}/trustGroups")
    public ResponseEntity<TrustGroupUpdateResponseDTO> getTrustGroupsByDepartment(
            @PathVariable("departmentId") UUID departmentId,
            WebRequest request) {
        TrustGroupUpdateResponseDTO responseDTO = null;
        try {
            if (!isOrganizationDetailsExistsInHeader(request)) {
                throw new GenericAPIException("Organization details missing in header");
            }
            responseDTO = trustGroupService.getAllTrustGroupsByDepartment(
                    UUID.fromString(request.getHeader(Constants.ORG_COLLAB_HEADER)),
                    departmentId);
        } catch (Exception e) {
            throw new GenericAPIException("Something went wrong ::" + e.getLocalizedMessage(), e);
        }
        return ControllerResponse.getOkResponseEntity(responseDTO);
    }

    private boolean isOrganizationDetailsExistsInHeader(WebRequest request) {
        return request.getHeader(Constants.ORG_COLLAB_HEADER) != null;
    }

    @PutMapping("/departments/{departmentId}/trustGroups")
    public ResponseEntity<TrustGroupUpdateDTO> updateTrustGroup(
            @RequestBody TrustGroupUpdateDTO requestDTO,
            @PathVariable("departmentId") UUID departmentId,
            WebRequest request) {
        if (!isOrganizationDetailsExistsInHeader(request)) {
            throw new GenericAPIException("Organization details missing in header");
        }
        trustGroupService.updateTrustGroups(requestDTO);
        return ControllerResponse.getOkResponseEntity(requestDTO);
    }

    @GetMapping("/trustGroups/{trustGroupId}/role")
    public ResponseEntity<String> getTrustGroupRole(@PathVariable("trustGroupId") UUID trustGroupId) {
        String role = trustGroupService.getRoleBasedOnFlavour(trustGroupId);
        return ControllerResponse.getOkResponseEntity(role);
    }
}

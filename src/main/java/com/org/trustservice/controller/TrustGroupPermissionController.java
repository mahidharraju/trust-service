package com.org.trustservice.controller;

import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.excpetion.GenericAPIException;
import com.org.trustservice.service.ITrustGroupService;
import com.org.trustservice.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@RestController
public class TrustGroupPermissionController {

  /**
   * trustGroupService bean.
   */
  private final ITrustGroupService trustGroupService;

  /**
   * create a new TrustGroupPermissionController.
   *
   * @param trustGroupService the service bean class
   */
  public TrustGroupPermissionController(
      final ITrustGroupService trustGroupService) {
    this.trustGroupService = trustGroupService;
  }

  /**
   * This method reads Department details and collaboration platform
   * details and query all the Trust groups with that combination.
   *
   * @param departmentId department identifier
   * @param request      WebRequest object
   * @return DTO with department wise trustgroup information.
   */
  @GetMapping("/departments/{departmentId}/trustGroups")
  public ResponseEntity<TrustGroupUpdateResponseDTO> getTrustGroupsByDepartment(
      @PathVariable("departmentId") final Long departmentId,
      final WebRequest request) {
    TrustGroupUpdateResponseDTO responseDto = null;
    if (request.getHeader(Constants.ORG_COLLAB_HEADER) == null) {
      throw new GenericAPIException("Organization details missing in header");
    }
    responseDto = trustGroupService.getAllTrustGroupsByDepartment(
        Long.parseLong(request.getHeader(Constants.ORG_COLLAB_HEADER)),
        departmentId);
    return ResponseEntity.ok(responseDto);
  }


  /**
   * Update the definition of trust group.
   *
   * @param updateRequest request dto object
   * @param departmentId  department identifier
   * @param request       web request object
   * @return returns the updated trust group info.
   */
  @PutMapping("/departments/{departmentId}/trustGroups")
  public ResponseEntity<TrustGroupUpdateDTO> updateTrustGroup(
      @RequestBody final TrustGroupUpdateDTO updateRequest,
      @PathVariable("departmentId") final Long departmentId,
      final WebRequest request) {
    if (request.getHeader(Constants.ORG_COLLAB_HEADER) == null) {
      throw new GenericAPIException("Organization details missing in header");
    }
    trustGroupService.updateTrustGroups(updateRequest);
    return ResponseEntity.ok(updateRequest);
  }

  /**
   * Gets Role for a trust group.
   *
   * @param trustGroupId trust group Identifier
   * @return Role value.
   */
  @GetMapping("/trustGroups/{trustGroupId}/role")
  public ResponseEntity<String> getTrustGroupRole(
      @PathVariable("trustGroupId") final Long trustGroupId) {
    String role = trustGroupService.getRoleBasedOnFlavour(trustGroupId);
    return ResponseEntity.ok(role);
  }
}

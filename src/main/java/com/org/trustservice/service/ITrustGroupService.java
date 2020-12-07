package com.org.trustservice.service;

import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;

public interface ITrustGroupService {

  /**
   * get trustgroup details for a given department.
   * @param orgCollabId
   * @param departmentId
   * @return TrustGroupUpdateResponseDTO.
   */
  TrustGroupUpdateResponseDTO getAllTrustGroupsByDepartment(
      Long orgCollabId,
      Long departmentId);

  /**
   * updates trustgroup.
   * @param requestDTO
   */
  void updateTrustGroups(TrustGroupUpdateDTO requestDTO);

  /**
   * returns role assigned to a trust group.
   * @param trustGroupId
   * @return role value
   */
  String getRoleBasedOnFlavour(Long trustGroupId);
}

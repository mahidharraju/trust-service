package com.org.trustservice.service;

import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.model.TrustGroupFlavour;

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
   * @return
   */
  TrustGroupFlavour updateTrustGroups(TrustGroupUpdateDTO requestDTO);

  /**
   * returns role assigned to a trust group.
   * @param trustGroupId
   * @return role value
   */
  String getRoleBasedOnFlavour(Long trustGroupId);
}

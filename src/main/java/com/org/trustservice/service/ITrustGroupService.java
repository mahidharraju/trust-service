package com.org.trustservice.service;

import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;

public interface ITrustGroupService {

  TrustGroupUpdateResponseDTO getAllTrustGroupsByDepartment(
      Long orgCollabId,
      Long departmentId);

  void updateTrustGroups(TrustGroupUpdateDTO requestDTO);

  String getRoleBasedOnFlavour(Long trustGroupId);
}

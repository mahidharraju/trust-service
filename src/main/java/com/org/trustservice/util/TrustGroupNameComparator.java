package com.org.trustservice.util;

import com.org.trustservice.dto.TrustGroupUpdateDTO;
import java.util.Comparator;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TrustGroupNameComparator implements Comparator<TrustGroupUpdateDTO> {

  @Override
  public int compare(
      final TrustGroupUpdateDTO o1,
      final TrustGroupUpdateDTO o2) {
    return o1.getName().compareTo(o2.getName());
  }
}

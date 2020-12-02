package com.org.trustservice.util;

import com.org.trustservice.dto.TrustGroupUpdateDTO;
import java.util.Comparator;

public class TrustGroupNameComparator implements Comparator<TrustGroupUpdateDTO> {

  @Override
  public int compare(TrustGroupUpdateDTO o1, TrustGroupUpdateDTO o2) {
    return o1.getName().compareTo(o2.getName());
  }
}

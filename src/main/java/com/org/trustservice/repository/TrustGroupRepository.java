package com.org.trustservice.repository;

import com.org.trustservice.model.TrustGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrustGroupRepository extends JpaRepository<TrustGroup, Long> {
}

package com.org.trustservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.trustservice.model.TrustGroup;

public interface TrustGroupRepository extends JpaRepository<TrustGroup, UUID> {

}

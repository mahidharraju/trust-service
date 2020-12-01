package com.org.trustservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.trustservice.model.TrustGroupFlavour;

public interface TrustGroupFlavourRepository extends JpaRepository<TrustGroupFlavour, UUID> {

}

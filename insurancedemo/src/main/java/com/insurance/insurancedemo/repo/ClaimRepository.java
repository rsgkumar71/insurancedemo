package com.insurance.insurancedemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.insurancedemo.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
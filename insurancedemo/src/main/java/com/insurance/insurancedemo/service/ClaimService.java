package com.insurance.insurancedemo.service;

import java.util.List;
import java.util.Optional; // Add this line to import the 'Claim' class

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.insurancedemo.model.Claim;
import com.insurance.insurancedemo.repo.ClaimRepository;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    public Optional<Claim> getByClaimId(Long claimId) {
        return claimRepository.findById(claimId);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Claim saveClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public void deleteClaim(Long claimId) {
        claimRepository.deleteById(claimId);
    }

    public Claim updateClaim(Long claimId, Claim claimDetails) {
        return claimRepository.findById(claimId).map(claim -> {
            claim.setClaimAmount(claimDetails.getClaimAmount());
            claim.setClaimReason(claimDetails.getClaimReason());
            claim.setClaimDate(claimDetails.getClaimDate());
            claim.setClaimStatus(claimDetails.getClaimStatus());
            claim.setPolicyId(claimDetails.getPolicyId());
            return claimRepository.save(claim);
        }).orElseGet(() -> {
            claimDetails.setClaimId(claimId);
            return claimRepository.save(claimDetails);
        });

        
    }

    //Create a search method by fetching  key and fetching the result by it
    //

}
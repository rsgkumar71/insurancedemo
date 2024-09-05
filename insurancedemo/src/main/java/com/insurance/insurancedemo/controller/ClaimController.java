package com.insurance.insurancedemo.controller;


// Add import statement for the Claim class
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.insurancedemo.model.Claim;
import com.insurance.insurancedemo.service.ClaimService;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getByClaimId(@PathVariable Long id) {
        Optional<Claim> claim = claimService.getByClaimId(id);
        return claim.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }

    @PostMapping
    public Claim saveClaim(@RequestBody Claim claim) {
        return claimService.saveClaim(claim);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable Long id, @RequestBody Claim claimDetails) {
        Claim updatedClaim = claimService.updateClaim(id, claimDetails);
        return ResponseEntity.ok(updatedClaim);
    }
}
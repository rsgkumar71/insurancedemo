package com.insurance.insurancedemo.controller;

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

import com.insurance.insurancedemo.model.InsurancePolicy;
import com.insurance.insurancedemo.service.InsurancePolicyService;

@RestController
@RequestMapping("/api/insurance-policies")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicy> getByInsurancePolicyId(@PathVariable Long id) {
        Optional<InsurancePolicy> policy = insurancePolicyService.getByInsurancePolicyId(id);
        return policy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<InsurancePolicy> getAllInsurancePolicies() {
        return insurancePolicyService.getAllInsurancePolicies();
    }

    @PostMapping
    public InsurancePolicy saveInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy) {
        return insurancePolicyService.saveInsurancePolicy(insurancePolicy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurancePolicy(@PathVariable Long id) {
        insurancePolicyService.deleteInsurancePolicy(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsurancePolicy> updateInsurancePolicy(@PathVariable Long id, @RequestBody InsurancePolicy policyDetails) {
        InsurancePolicy updatedPolicy = insurancePolicyService.updateInsurancePolicy(id, policyDetails);
        return ResponseEntity.ok(updatedPolicy);
    }
}
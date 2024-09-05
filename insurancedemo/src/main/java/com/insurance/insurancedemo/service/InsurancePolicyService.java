package com.insurance.insurancedemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.insurancedemo.model.InsurancePolicy;
import com.insurance.insurancedemo.repo.InsurancePolicyRepository;

@Service
public class InsurancePolicyService {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    public Optional<InsurancePolicy> getByInsurancePolicyId(Long policyId) {
        return insurancePolicyRepository.findById(policyId);
    }

    public List<InsurancePolicy> getAllInsurancePolicies() {
        return insurancePolicyRepository.findAll();
    }

    public InsurancePolicy saveInsurancePolicy(InsurancePolicy insurancePolicy) {
        return insurancePolicyRepository.save(insurancePolicy);
    }

    public void deleteInsurancePolicy(Long policyId) {
        insurancePolicyRepository.deleteById(policyId);
    }

    public InsurancePolicy updateInsurancePolicy(Long policyId, InsurancePolicy policyDetails) {
        return insurancePolicyRepository.findById(policyId).map(policy -> {
            policy.setPolicyName(policyDetails.getPolicyName());
            policy.setPolicyType(policyDetails.getPolicyType());
            policy.setPolicyStartDate(policyDetails.getPolicyStartDate());
            policy.setPolicyEndDate(policyDetails.getPolicyEndDate());
            policy.setPolicyAmount(policyDetails.getPolicyAmount());
            policy.setPolicyStatus(policyDetails.getPolicyStatus());
            policy.setClientId(policyDetails.getClientId());
            return insurancePolicyRepository.save(policy);
        }).orElseGet(() -> {
            policyDetails.setPolicyId(policyId);
            return insurancePolicyRepository.save(policyDetails);
        });
    }
}
package com.insurance.insurancedemo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long claimId;

    @Column(name = "claim_amount")
    private Double claimAmount;

    @Column(name = "claim_reason")
    private String claimReason;

    @Column(name = "claim_date")
    private Date claimDate;

    @Column(name = "claim_status")
    private String claimStatus;

    @Column(name = "policy_id", insertable = false, updatable = false)
    private Long policyId;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private InsurancePolicy insurancePolicy;
}

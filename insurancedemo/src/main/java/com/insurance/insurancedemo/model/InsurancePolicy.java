package com.insurance.insurancedemo.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InsurancePolicy {



    //asdsadsadas
    @Id
    @Column(name = "policy_id")
    private Long policyId;

    @Column(name = "policy_name")
    private String policyName;

    @Column(name = "policy_type")
    private String policyType;

    @Column(name = "policy_start_date")
    private Date policyStartDate;

    @Column(name = "policy_end_date")
    private Date policyEndDate;

    @Column(name = "policy_amount")
    private Double policyAmount;

    @Column(name = "policy_status")
    private String policyStatus;

    @Column(name = "client_id", insertable = false, updatable = false)
    private Long clientId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "insurancePolicy")
    @JsonIgnore
    private List<Claim> claims;
}
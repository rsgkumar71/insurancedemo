package com.insurance.insurancedemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.insurancedemo.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
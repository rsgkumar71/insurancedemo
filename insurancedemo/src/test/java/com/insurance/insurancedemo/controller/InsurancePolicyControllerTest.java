package com.insurance.insurancedemo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.insurancedemo.model.Claim;
import com.insurance.insurancedemo.model.Client;
import com.insurance.insurancedemo.model.InsurancePolicy;
import com.insurance.insurancedemo.service.InsurancePolicyService;

@WebMvcTest(InsurancePolicyController.class)
public class InsurancePolicyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsurancePolicyService insurancePolicyService;

    @Autowired
    private ObjectMapper objectMapper;

    private InsurancePolicy insurancePolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Client client = new Client();
        client.setClientId(1L);
        client.setClientName("John Doe");

        // Create an InsurancePolicy object
         insurancePolicy = new InsurancePolicy();
        insurancePolicy.setPolicyId(1L);
        insurancePolicy.setPolicyName("Health Insurance");
        insurancePolicy.setPolicyType("Health");
        insurancePolicy.setPolicyStartDate(new Date());
        insurancePolicy.setPolicyEndDate(new Date());
        insurancePolicy.setPolicyAmount(1000.0);
        insurancePolicy.setPolicyStatus("Active");
        insurancePolicy.setClientId(client.getClientId());
        insurancePolicy.setClient(client);
        insurancePolicy.setClaims(new ArrayList<Claim>());

    }

    @Test
    void testGetByInsurancePolicyId() throws Exception {
        when(insurancePolicyService.getByInsurancePolicyId(anyLong())).thenReturn(Optional.of(insurancePolicy));

        mockMvc.perform(get("/api/insurance-policies/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByInsurancePolicyId_NotFound() throws Exception {
        when(insurancePolicyService.getByInsurancePolicyId(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/insurance-policies/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllInsurancePolicies() throws Exception {
        when(insurancePolicyService.getAllInsurancePolicies()).thenReturn(Arrays.asList(insurancePolicy));

        mockMvc.perform(get("/api/insurance-policies"))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveInsurancePolicy() throws Exception {
        when(insurancePolicyService.saveInsurancePolicy(any(InsurancePolicy.class))).thenReturn(insurancePolicy);

        mockMvc.perform(post("/api/insurance-policies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insurancePolicy)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteInsurancePolicy() throws Exception {
        mockMvc.perform(delete("/api/insurance-policies/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateInsurancePolicy() throws Exception {
        when(insurancePolicyService.updateInsurancePolicy(anyLong(), any(InsurancePolicy.class))).thenReturn(insurancePolicy);

        mockMvc.perform(put("/api/insurance-policies/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insurancePolicy)))
                .andExpect(status().isOk());
    }
}
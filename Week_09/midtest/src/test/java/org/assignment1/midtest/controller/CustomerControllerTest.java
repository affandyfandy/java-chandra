package org.assignment1.midtest.controller;

import org.assignment1.midtest.dto.CustomerDTO;
import org.assignment1.midtest.entity.Status;
import org.assignment1.midtest.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID customerId;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        customerDTO = CustomerDTO.builder()
                .id(customerId)
                .name("Ryan Chandra")
                .phoneNumber("+6282219095915")
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void CustomerController_getAllCustomers_ReturnsAllCustomers() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CustomerDTO> page = new PageImpl<>(Collections.singletonList(customerDTO), pageable, 1);

        when(customerService.getAllCustomers(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void CustomerController_getCustomerById_ReturnsCustomer() throws Exception {
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customerDTO));

        mockMvc.perform(get("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ryan Chandra"));
    }

    @Test
    void CustomerController_getCustomerById_ReturnsNotFound() throws Exception {
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void CustomerController_createCustomer_ReturnsCreatedCustomer() throws Exception {
        when(customerService.createCustomer(any(CustomerDTO.class))).thenReturn(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ryan Chandra"));
    }

    @Test
    void CustomerController_editCustomer_ReturnsUpdatedCustomer() throws Exception {
        when(customerService.editCustomer(eq(customerId), any(CustomerDTO.class))).thenReturn(Optional.of(customerDTO));

        mockMvc.perform(put("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ryan Chandra"));
    }

    @Test
    void CustomerController_editCustomer_ReturnsNotFound() throws Exception {
        when(customerService.editCustomer(eq(customerId), any(CustomerDTO.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void CustomerController_changeCustomerStatus_ReturnsUpdatedCustomer() throws Exception {
        when(customerService.changeCustomerStatus(customerId)).thenReturn(Optional.of(customerDTO));

        mockMvc.perform(patch("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void CustomerController_changeCustomerStatus_ReturnsNotFound() throws Exception {
        when(customerService.changeCustomerStatus(customerId)).thenReturn(Optional.empty());

        mockMvc.perform(patch("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void CustomerController_deleteCustomer_ReturnsSuccessMessage() throws Exception {
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customerDTO));
        doNothing().when(customerService).deleteCustomer(customerId);

        mockMvc.perform(delete("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer record deleted successfully."));
    }

    @Test
    void CustomerController_deleteCustomer_ReturnsNotFound() throws Exception {
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found."));
    }

}

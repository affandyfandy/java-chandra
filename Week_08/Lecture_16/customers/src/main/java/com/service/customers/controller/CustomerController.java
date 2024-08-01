package com.service.customers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.customers.dto.CustomerDTO;
import com.service.customers.service.CustomerServiceFeign;
import com.service.customers.service.CustomerServiceRestTemplate;
import com.service.customers.service.CustomerServiceWebClient;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerServiceFeign customerServiceFeign;
    private final CustomerServiceRestTemplate customerServiceRestTemplate;
    private final CustomerServiceWebClient customerServiceWebClient;

    @GetMapping("/feign/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerFeign(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerServiceFeign.getCustomerById(customerId));
    }

    @GetMapping("/rest-template/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerRestTemplate(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerServiceRestTemplate.getCustomerById(customerId));
    }

    @GetMapping("/web-client/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable Long customerId) {
        return customerServiceWebClient.getCustomerById(customerId);
    }
}

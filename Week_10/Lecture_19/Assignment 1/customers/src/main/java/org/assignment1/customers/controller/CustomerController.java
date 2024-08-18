package org.assignment1.customers.controller;

import lombok.AllArgsConstructor;
import org.assignment1.customers.dto.CustomerDTO;
import org.assignment1.customers.service.CustomerServiceFeign;
import org.assignment1.customers.service.CustomerServiceRestTemplate;
import org.assignment1.customers.service.CustomerServiceWebClient;
import org.assignment1.customers.service.impl.CustomerServiceFeignImpl;
import org.assignment1.customers.service.impl.CustomerServiceRestTemplateImpl;
import org.assignment1.customers.service.impl.CustomerServiceWebClientImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerServiceFeignImpl customerServiceFeign;
    private final CustomerServiceRestTemplateImpl customerServiceRestTemplate;
    private final CustomerServiceWebClientImpl customerServiceWebClient;

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

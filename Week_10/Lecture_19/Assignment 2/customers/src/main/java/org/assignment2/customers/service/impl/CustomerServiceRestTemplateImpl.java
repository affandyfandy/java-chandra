package org.assignment2.customers.service.impl;

import lombok.AllArgsConstructor;
import org.assignment2.customers.dto.CustomerDTO;
import org.assignment2.customers.dto.ProductDTO;
import org.assignment2.customers.entity.Customer;
import org.assignment2.customers.exception.ResourceNotFoundException;
import org.assignment2.customers.repository.CustomerRepository;
import org.assignment2.customers.resttemplate.ProductRestTemplate;
import org.assignment2.customers.service.CustomerServiceRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceRestTemplateImpl implements CustomerServiceRestTemplate {

    private final CustomerRepository customerRepository;
    private final ProductRestTemplate productClientRT;

    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<ProductDTO> products = productClientRT.getProductsByCustomerId(customerId);

        return new CustomerDTO(customer.getId(), customer.getName(), products);
    }
}
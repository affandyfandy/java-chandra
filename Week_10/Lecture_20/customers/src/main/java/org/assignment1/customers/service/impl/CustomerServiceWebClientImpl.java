package org.assignment1.customers.service.impl;

import lombok.AllArgsConstructor;
import org.assignment1.customers.dto.CustomerDTO;
import org.assignment1.customers.dto.ProductDTO;
import org.assignment1.customers.entity.Customer;
import org.assignment1.customers.exception.ResourceNotFoundException;
import org.assignment1.customers.repository.CustomerRepository;
import org.assignment1.customers.service.CustomerServiceWebClient;
import org.assignment1.customers.webclient.ProductWebClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceWebClientImpl implements CustomerServiceWebClient {
    private final CustomerRepository customerRepository;
    private final ProductWebClient productClientWC;

    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<ProductDTO> products = productClientWC.getProductsByCustomerId(customerId);

        return new CustomerDTO(customer.getId(), customer.getName(), products);
    }
}
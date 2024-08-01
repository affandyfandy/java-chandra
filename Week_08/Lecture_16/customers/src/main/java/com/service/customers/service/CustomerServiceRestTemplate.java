package com.service.customers.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.service.customers.dto.CustomerDTO;
import com.service.customers.dto.ProductDTO;
import com.service.customers.entity.Customer;
import com.service.customers.exception.ResourceNotFoundException;
import com.service.customers.repository.CustomerRepository;
import com.service.customers.resttemplate.ProductClientRT;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceRestTemplate {

    private final CustomerRepository customerRepository;
    private final ProductClientRT productClientRT;

    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<ProductDTO> products = productClientRT.getProductsByCustomerId(customerId);

        return new CustomerDTO(customer.getId(), customer.getName(), products);
    }
}

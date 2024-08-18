package org.assignment2.customers.service.impl;

import lombok.AllArgsConstructor;
import org.assignment2.customers.dto.CustomerDTO;
import org.assignment2.customers.dto.ProductDTO;
import org.assignment2.customers.entity.Customer;
import org.assignment2.customers.exception.ResourceNotFoundException;
import org.assignment2.customers.feign.ProductFeign;
import org.assignment2.customers.repository.CustomerRepository;
import org.assignment2.customers.service.CustomerServiceFeign;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceFeignImpl implements CustomerServiceFeign {

    private final CustomerRepository customerRepository;
    private final ProductFeign productClient;

    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<ProductDTO> products = productClient.getProductsByCustomerId(customerId);

        return new CustomerDTO(customer.getId(), customer.getName(), products);
    }
}

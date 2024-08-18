package org.assignment2.customers.service;

import org.assignment2.customers.dto.CustomerDTO;

public interface CustomerServiceRestTemplate {
    public CustomerDTO getCustomerById(Long customerId);
}

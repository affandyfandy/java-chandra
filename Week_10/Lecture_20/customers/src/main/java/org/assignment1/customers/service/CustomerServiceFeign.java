package org.assignment1.customers.service;

import org.assignment1.customers.dto.CustomerDTO;

public interface CustomerServiceFeign {
    public CustomerDTO getCustomerById(Long customerId);
}

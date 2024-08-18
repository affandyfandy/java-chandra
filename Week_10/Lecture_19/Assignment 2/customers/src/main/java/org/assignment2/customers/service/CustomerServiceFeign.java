package org.assignment2.customers.service;

import org.assignment2.customers.dto.CustomerDTO;

public interface CustomerServiceFeign {
    public CustomerDTO getCustomerById(Long customerId);
}

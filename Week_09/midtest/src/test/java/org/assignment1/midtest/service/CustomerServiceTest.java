package org.assignment1.midtest.service;

import org.assertj.core.api.Assertions;
import org.assignment1.midtest.dto.CustomerDTO;
import org.assignment1.midtest.entity.Customer;
import org.assignment1.midtest.entity.Status;
import org.assignment1.midtest.mapper.CustomerMapper;
import org.assignment1.midtest.repository.CustomerRepository;
import org.assignment1.midtest.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerDTO customerDTO;
    private UUID customerId;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        customer = Customer.builder()
                .id(customerId)
                .name("Ryan")
                .phoneNumber("+6282219099990")
                .status(Status.ACTIVE)
                .build();

        customerDTO = CustomerDTO.builder()
                .id(customerId)
                .name("Ryan")
                .phoneNumber("+6282219099990")
                .status(Status.ACTIVE)
                .build();
    }

    @AfterEach
    void tearDown() {
        customer = null;
        customerDTO = null;
        customerId = null;
    }

    @Test
    void CustomerService_createCustomer_ReturnSavedCustomers(){
        // Arrange
        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        // Act
        CustomerDTO savedCustomer = customerService.createCustomer(customerDTO);

        // Assertion
        Assertions.assertThat(savedCustomer).isNotNull();
    }

    @Test
    void CustomerService_getAllCustomers_ReturnCustomerPage() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Customer> customerPage = new PageImpl<>(Collections.singletonList(customer));

        when(customerRepository.findAll(pageable)).thenReturn(customerPage);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        // Act
        Page<CustomerDTO> result = customerService.getAllCustomers(pageable);

        // Assertion
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void CustomerService_getCustomerById_ReturnCustomerDTO() {
        // Arrange
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        // Act
        Optional<CustomerDTO> result = customerService.getCustomerById(customerId);

        // Assertion
        Assertions.assertThat(result).isPresent();
    }

    @Test
    void CustomerService_editCustomer_ReturnUpdatedCustomerDTO() {
        // Arrange
        CustomerDTO updatedCustomerDTO = CustomerDTO.builder()
                .id(customerId)
                .name("Ryan Updated")
                .phoneNumber("+6282219099991")
                .status(Status.ACTIVE)
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDTO(Mockito.any(Customer.class))).thenReturn(updatedCustomerDTO);

        // Act
        Optional<CustomerDTO> result = customerService.editCustomer(customerId, updatedCustomerDTO);

        // Assertion
        Assertions.assertThat(result).isPresent();
    }

    @Test
    void CustomerService_changeCustomerStatus_ReturnUpdatedStatus() {
        // Arrange
        Customer updatedCustomer = Customer.builder()
                .id(customerId)
                .name("Ryan")
                .phoneNumber("+6282219099990")
                .status(Status.INACTIVE)
                .build();

        CustomerDTO updatedCustomerDTO = CustomerDTO.builder()
                .id(customerId)
                .name("Ryan")
                .phoneNumber("+6282219099990")
                .status(Status.INACTIVE)
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(updatedCustomer);
        when(customerMapper.toDTO(updatedCustomer)).thenReturn(updatedCustomerDTO);

        // Act
        Optional<CustomerDTO> result = customerService.changeCustomerStatus(customerId);

        // Assertion
        Assertions.assertThat(result).isPresent();
    }

    @Test
    void CustomerService_deleteCustomer_DeletedCustomer() {
        // Act
        customerService.deleteCustomer(customerId);

        // Assertion
        assertAll(
                () -> Mockito.verify(customerRepository, Mockito.times(1)).deleteById(customerId)
        );
    }
}

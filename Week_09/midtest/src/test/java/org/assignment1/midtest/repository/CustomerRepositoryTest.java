package org.assignment1.midtest.repository;

import org.assignment1.midtest.entity.Customer;
import org.assignment1.midtest.entity.Status;
import org.junit.jupiter.api.AfterEach;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private UUID customerId1;
    private UUID customerId2;

    @BeforeEach
    void setUp() {
        Customer customer1 = Customer.builder()
                .name("Chandra")
                .phoneNumber("+6282219095915")
                .status(Status.ACTIVE)
                .build();

        Customer customer2 = Customer.builder()
                .name("Hadi")
                .phoneNumber("+6282219095910")
                .status(Status.ACTIVE)
                .build();

        customer1 = customerRepository.save(customer1);
        customer2 = customerRepository.save(customer2);

        customerId1 = customer1.getId();
        customerId2 = customer2.getId();
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void CustomerRepository_findByName_ReturnsCustomer() {
        // Act
        Optional<Customer> customer = customerRepository.findByName("Chandra");

        // Assertion
        Assertions.assertThat(customer).isPresent();
        Assertions.assertThat(customer.get().getName()).isEqualTo("Chandra");
    }

    @Test
    void CustomerRepository_findById_ReturnsCustomer() {
        // Act
        Optional<Customer> customer = customerRepository.findById(customerId1);

        // Assertion
        Assertions.assertThat(customer).isPresent();
    }

    @Test
    void CustomerRepository_save_ReturnsSavedCustomer() {
        // Arrange
        Customer customer3 = Customer.builder()
                .name("Ryan")
                .phoneNumber("+6282219099990")
                .status(Status.ACTIVE)
                .build();

        // Act
        Customer savedCustomer = customerRepository.save(customer3);

        // Assertion
        Assertions.assertThat(savedCustomer).isNotNull();
    }

    @Test
    void CustomerRepository_findAll_ReturnsAllCustomers() {
        // Act
        List<Customer> customers = customerRepository.findAll();

        // Assertion
        Assertions.assertThat(customers).hasSize(2);
    }

    @Test
    void CustomerRepository_updateCustomer_ReturnsUpdatedCustomers() {
        // Act
        Optional<Customer> customerOpt = customerRepository.findById(customerId1);

        Customer customer = customerOpt.get();
        customer.setName("Ryan Holy");
        customer.setPhoneNumber("+62899999999");

        customerRepository.save(customer);
        Customer updatedCustomer = customerRepository.findById(customerId1).orElseThrow();

        // Assertion
        Assertions.assertThat(customerOpt).isPresent();
        Assertions.assertThat(updatedCustomer.getName()).isEqualTo("Ryan Holy");
        Assertions.assertThat(updatedCustomer.getPhoneNumber()).isEqualTo("+62899999999");
    }

    @Test
    void CustomerRepository_deleteCustomer_ReturnsDeletedCustomer() {
        // Act
        customerRepository.deleteById(customerId1);

        // Assertion
        Optional<Customer> customerOpt = customerRepository.findById(customerId2);
        Assertions.assertThat(customerOpt).isPresent();
    }
}


package org.assignment1.midtest.mapper;


import org.assignment1.midtest.dto.CustomerDTO;
import org.assignment1.midtest.entity.Customer;
import org.assignment1.midtest.entity.Status;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void CustomerMapper_testToDTO() {
        // Given
        Customer customer = new Customer();
        UUID customerId = UUID.randomUUID();
        customer.setId(customerId);
        customer.setName("John Doe");
        customer.setPhoneNumber("+6281234567890");
        customer.setStatus(Status.ACTIVE);

        // When
        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        // Then
        assertThat(customerDTO).isNotNull();
        assertThat(customerDTO.getId()).isEqualTo(customerId);
        assertThat(customerDTO.getName()).isEqualTo("John Doe");
        assertThat(customerDTO.getPhoneNumber()).isEqualTo("+6281234567890");
        assertThat(customerDTO.getStatus()).isEqualTo(Status.ACTIVE);
    }

    @Test
    void CustomerMapper_testToEntity() {
        // Given
        UUID customerId = UUID.randomUUID();
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(customerId)
                .name("Jane Doe")
                .phoneNumber("+6289876543210")
                .status(Status.INACTIVE)
                .build();

        // When
        Customer customer = customerMapper.toEntity(customerDTO);

        // Then
        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isEqualTo(customerId);
        assertThat(customer.getName()).isEqualTo("Jane Doe");
        assertThat(customer.getPhoneNumber()).isEqualTo("+6289876543210");
        assertThat(customer.getStatus()).isEqualTo(Status.INACTIVE);
    }
}

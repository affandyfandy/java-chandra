package org.assignment1.customers.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;
}

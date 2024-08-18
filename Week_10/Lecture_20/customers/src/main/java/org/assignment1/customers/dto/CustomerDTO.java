package org.assignment1.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;
}

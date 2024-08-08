package org.assignment1.midtest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;

import org.assignment1.midtest.dto.ProductDTO;
import org.assignment1.midtest.entity.Product;
import org.assignment1.midtest.entity.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Test
    void ProductMapperTest_testToDTO() {
        Product product = createProduct();

        ProductDTO productDTO = productMapper.toDTO(product);

        assertNotNull(productDTO);
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getStatus(), productDTO.getStatus());
    }

    @Test
    void ProductMapperTest_testToEntity() {
        ProductDTO productDTO = createProductDTO();

        Product product = productMapper.toEntity(productDTO);

        assertNotNull(product);
        assertEquals(productDTO.getId(), product.getId());
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getPrice(), product.getPrice());
        assertEquals(productDTO.getStatus(), product.getStatus());
    }

    private Product createProduct() {
        return Product.builder()
                .id(UUID.randomUUID())
                .name("Mac Book")
                .price(BigDecimal.valueOf(899.00))
                .status(Status.ACTIVE)
                .build();
    }

    private ProductDTO createProductDTO() {
        return ProductDTO.builder()
                .id(UUID.randomUUID())
                .name("Mac Book")
                .price(BigDecimal.valueOf(899.00))
                .status(Status.ACTIVE)
                .build();
    }
}

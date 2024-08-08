package org.assignment1.midtest.repository;

import org.assertj.core.api.Assertions;
import org.assignment1.midtest.entity.Product;
import org.assignment1.midtest.entity.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private UUID productId1;
    private UUID productId2;

    // Arrange
    @BeforeEach
    void setUp() {
        Product product1 = Product.builder()
                .name("Product A")
                .price(BigDecimal.valueOf(15.99))
                .status(Status.ACTIVE)
                .build();

        Product product2 = Product.builder()
                .name("Product B")
                .price(BigDecimal.valueOf(25.99))
                .status(Status.INACTIVE)
                .build();

        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);

        productId1 = product1.getId();
        productId2 = product2.getId();
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void ProductRepository_FindAll_ReturnProduct() {
        // Act
        Page<Product> products = productRepository.findAll(PageRequest.of(0, 10));

        // Assertion
        Assertions.assertThat(products.getTotalElements()).isEqualTo(2);
    }

    @Test
    void ProductRepository_FindByStatus_ReturnProduct() {
        // Act
        Page<Product> products = productRepository.findByStatus(Status.ACTIVE, PageRequest.of(0, 10));

        // Assertion
        Assertions.assertThat(products.getTotalElements()).isEqualTo(1);


    }

    @Test
    void ProductRepository_FindByStatusAndProduct_ReturnProduct() {
        // Act
        Page<Product> products = productRepository.findByNameContainingIgnoreCaseAndStatus("Product", Status.ACTIVE, PageRequest.of(0, 10));

        // Assertion
        Assertions.assertThat(products.getTotalElements()).isEqualTo(1);
    }

    @Test
    void ProductRepository_FindByProductIgnoreCase_ReturnProduct() {
        // Act
        Page<Product> products = productRepository.findByNameContainingIgnoreCase("product a", PageRequest.of(0, 10));

        // Assertion
        Assertions.assertThat(products.getTotalElements()).isEqualTo(1);
    }

    @Test
    void ProductRepository_save_ReturnSavedProduct() {
        // Arrange
        Product product3 = Product.builder()
                .name("Mac Air 999GB")
                .price(BigDecimal.valueOf(200.99))
                .status(Status.ACTIVE)
                .build();

        // Act
        Product savedProduct = productRepository.save(product3);

        // Assertion
        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    void ProductRepository_UpdateData_ReturnProduct() {
        // Act
        Optional<Product> productOpt = productRepository.findById(productId1);

        Product product = productOpt.get();
        product.setPrice(BigDecimal.valueOf(18.99));

        productRepository.save(product);
        Product updatedProduct = productRepository.findById(productId1).orElseThrow();

        // Assertion
        Assertions.assertThat(productOpt).isPresent();
        Assertions.assertThat(updatedProduct.getPrice()).isEqualTo(BigDecimal.valueOf(18.99));

    }

    @Test
    void ProductRepository_DeleteData_ReturnProduct() {
        // Act
        productRepository.deleteById(productId2);

        // Assertion
        Optional<Product> deletedProduct = productRepository.findById(productId2);
        Assertions.assertThat(deletedProduct).isNotPresent();
    }
}

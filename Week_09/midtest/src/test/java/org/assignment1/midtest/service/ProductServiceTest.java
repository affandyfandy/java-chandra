package org.assignment1.midtest.service;

import org.assertj.core.api.Assertions;
import org.assignment1.midtest.dto.ProductDTO;
import org.assignment1.midtest.entity.Product;
import org.assignment1.midtest.entity.Status;
import org.assignment1.midtest.mapper.ProductMapper;
import org.assignment1.midtest.repository.ProductRepository;
import org.assignment1.midtest.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private UUID productId;
    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = Product.builder()
                .id(productId)
                .name("Product A")
                .price(BigDecimal.valueOf(100))
                .status(Status.ACTIVE)
                .build();

        productDTO = ProductDTO.builder()
                .id(productId)
                .name("Product A")
                .price(BigDecimal.valueOf(100))
                .status(Status.ACTIVE)
                .build();
    }

    @AfterEach
    void tearDown() {
        productId = null;
        product = null;
        productDTO = null;
    }

    @Test
    void ProductService_createProduct_ReturnSavedProduct() {
        // Arrange
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // Act
        ProductDTO savedProduct = productService.createProduct(productDTO);

        // Assertion
        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    void ProductService_getAllProducts_ReturnProductPage() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product));

        when(productRepository.findAll(pageable)).thenReturn(productPage);
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        // Act
        Page<ProductDTO> result = productService.getAllProducts(pageable);

        // Assertion
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void ProductService_getProductById_ReturnProductDTO() {
        // Arrange
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        // Act
        Optional<ProductDTO> result = productService.getProductById(productId);

        // Assertion
        Assertions.assertThat(result).isPresent();
    }

    @Test
    void ProductService_updateProduct_ReturnUpdatedProductDTO() {
        // Arrange
        Product updatedProduct = Product.builder().id(productId).name("Product A Updated").price(BigDecimal.valueOf(150)).status(Status.ACTIVE).build();

        ProductDTO updatedProductDTO = ProductDTO.builder().id(productId).name("Product A Updated").price(BigDecimal.valueOf(150)).status(Status.ACTIVE).build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toEntity(updatedProductDTO)).thenReturn(updatedProduct);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);
        when(productMapper.toDTO(updatedProduct)).thenReturn(updatedProductDTO);

        // Act
        Optional<ProductDTO> result = productService.updateProduct(productId, updatedProductDTO);

        // Assertion
        Assertions.assertThat(result).isPresent();
    }

    @Test
    void ProductService_toggleProductStatus_ReturnUpdatedStatus() {
        // Arrange
        Product updatedProduct = Product.builder().id(productId).name("Product A").price(BigDecimal.valueOf(100)).status(Status.INACTIVE).build();

        ProductDTO updatedProductDTO = ProductDTO.builder().id(productId).name("Product A").price(BigDecimal.valueOf(100)).status(Status.INACTIVE).build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);
        when(productMapper.toDTO(updatedProduct)).thenReturn(updatedProductDTO);

        // Act
        Optional<ProductDTO> result = productService.toggleProductStatus(productId);

        // Assertion
        Assertions.assertThat(result).isPresent();
    }

    @Test
    void ProductService_deleteProduct_VerifyDeletion() {
        // Act
        productService.deleteProduct(productId);

        // Assertion
        assertAll(
                () -> Mockito.verify(productRepository, Mockito.times(1)).deleteById(productId)
        );
    }

    @Test
    void ProductService_searchProducts_ReturnProductPage() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product));

        when(productRepository.findByNameContainingIgnoreCaseAndStatus("Product", Status.ACTIVE, pageable)).thenReturn(productPage);
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        // Act
        Page<ProductDTO> result = productService.searchProducts("Product", Status.ACTIVE, pageable);

        // Assertion
        Assertions.assertThat(result).isNotNull();
    }
}

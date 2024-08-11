package org.assignment1.midtest.mapper;

import org.assignment1.midtest.dto.InvoiceProductDTO;
import org.assignment1.midtest.dto.InvoiceProductWithoutProductIdDTO;
import org.assignment1.midtest.entity.Invoice;
import org.assignment1.midtest.entity.InvoiceProduct;
import org.assignment1.midtest.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceProductMapperTest {
    @InjectMocks
    private InvoiceProductMapperImpl invoiceProductMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ProductMapperTest_toInvoiceProductDTO_ShouldMapCorrectly() {
        // Arrange
        UUID invoiceId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Invoice invoice = Invoice.builder().id(invoiceId).build();
        Product product = Product.builder().id(productId).name("Product A").build();
        InvoiceProduct invoiceProduct = InvoiceProduct.builder()
                .invoice(invoice)
                .product(product)
                .productName("Product A")
                .quantity(2)
                .amount(BigDecimal.valueOf(200))
                .build();

        // Act
        InvoiceProductDTO result = invoiceProductMapper.toInvoiceProductDTO(invoiceProduct);

        // Assert
        assertNotNull(result);
        assertEquals(invoiceId, result.getInvoiceId());
        assertEquals(productId, result.getProductId());
        assertEquals("Product A", result.getProductName());
        assertEquals(2, result.getQuantity());
        assertEquals(BigDecimal.valueOf(200), result.getAmount());
    }

    @Test
    void ProductMapperTest_toInvoiceProduct_ShouldMapCorrectly() {
        // Arrange
        UUID invoiceId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Invoice invoice = Invoice.builder().id(invoiceId).build();
        Product product = Product.builder().id(productId).name("Product A").build();
        InvoiceProductDTO invoiceProductDTO = InvoiceProductDTO.builder()
                .invoiceId(invoiceId)
                .productId(productId)
                .productName("Product A")
                .quantity(2)
                .amount(BigDecimal.valueOf(200))
                .build();

        // Act
        InvoiceProduct result = invoiceProductMapper.toInvoiceProduct(invoiceProductDTO, invoice, product);

        // Assert
        assertNotNull(result);
        assertEquals(invoiceId, result.getInvoice().getId());
        assertEquals(productId, result.getProduct().getId());
        assertEquals("Product A", result.getProductName());
        assertEquals(2, result.getQuantity());
        assertEquals(BigDecimal.valueOf(200), result.getAmount());
    }

    @Test
    void ProductMapperTest_toInvoiceProductWithoutIdDTO_ShouldMapCorrectly() {
        // Arrange
        UUID invoiceId = UUID.randomUUID();
        Product product = Product.builder().name("Product A").price(BigDecimal.valueOf(100)).build();
        Invoice invoice = Invoice.builder().id(invoiceId).build();
        InvoiceProduct invoiceProduct = InvoiceProduct.builder()
                .invoice(invoice)
                .product(product)
                .productName("Product A")
                .quantity(2)
                .amount(BigDecimal.valueOf(200))
                .build();

        // Act
        InvoiceProductWithoutProductIdDTO result = invoiceProductMapper.toInvoiceProductWithoutIdDTO(invoiceProduct);

        // Assert
        assertNotNull(result);
        assertEquals(invoiceId, result.getInvoiceId());
        assertEquals("Product A", result.getProductName());
        assertEquals(2, result.getQuantity());
        assertEquals(BigDecimal.valueOf(200), result.getAmount());
        assertEquals(BigDecimal.valueOf(100), result.getPrice());
    }

    @Test
    void ProductMapperTest_toInvoiceProductFromWithoutIdDTO_ShouldMapCorrectly() {
        // Arrange
        UUID invoiceId = UUID.randomUUID();
        Invoice invoice = Invoice.builder().id(invoiceId).build();
        Product product = Product.builder().name("Product A").price(BigDecimal.valueOf(100)).build();
        InvoiceProductWithoutProductIdDTO invoiceProductWithoutProductIdDTO = InvoiceProductWithoutProductIdDTO.builder()
                .invoiceId(invoiceId)
                .productName("Product A")
                .quantity(2)
                .amount(BigDecimal.valueOf(200))
                .price(BigDecimal.valueOf(100))
                .build();

        // Act
        InvoiceProduct result = invoiceProductMapper.toInvoiceProduct(invoiceProductWithoutProductIdDTO, invoice, product);

        // Assert
        assertNotNull(result);
        assertEquals(invoiceId, result.getInvoice().getId()); // Compare by ID
        assertEquals(product.getName(), result.getProductName());
        assertEquals(2, result.getQuantity());
        assertEquals(BigDecimal.valueOf(200), result.getAmount());
    }

}

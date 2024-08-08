package org.assignment1.midtest.repository;

import org.assignment1.midtest.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InvoiceProductRepositoryTest {

    @Autowired
    private InvoiceProductRepository invoiceProductRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private UUID productId;
    private UUID invoiceId;
    private InvoiceProductId invoiceProductId;


    // Arrange
    @BeforeEach
    void setUp() {
        // Create a customer
        Customer customer = Customer.builder()
                .name("Customer A")
                .phoneNumber("+621234567890")
                .status(Status.ACTIVE)
                .build();
        customer = customerRepository.save(customer);
        UUID customerId = customer.getId();

        // Create a product
        Product product = Product.builder()
                .name("Product A")
                .price(BigDecimal.valueOf(10.00))
                .status(Status.ACTIVE)
                .build();
        product = productRepository.save(product);
        productId = product.getId();

        // Create an invoice
        Invoice invoice = Invoice.builder()
                .customer(customer)
                .invoiceAmount(BigDecimal.valueOf(100.00))
                .invoiceDate(LocalDateTime.now())
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
        invoice = invoiceRepository.save(invoice);
        invoiceId = invoice.getId();

        // Create an InvoiceProduct
        InvoiceProduct invoiceProduct = InvoiceProduct.builder()
                .invoice(invoice)
                .product(product)
                .productName(product.getName())
                .quantity(5)
                .amount(BigDecimal.valueOf(50.00))
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
        invoiceProduct = invoiceProductRepository.save(invoiceProduct);
        invoiceProductId = new InvoiceProductId(invoiceId, productId);
    }

    @AfterEach
    void tearDown() {
        invoiceProductRepository.deleteAll();
        invoiceRepository.deleteAll();
        productRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void InvoiceProductRepository_FindById_ReturnInvoiceProduct() {
        // Act
        InvoiceProduct invoiceProduct = invoiceProductRepository.findById(invoiceProductId).orElse(null);

        // Assertion
        assertThat(invoiceProduct).isNotNull();
        assertThat(invoiceProduct.getInvoice().getId()).isEqualTo(invoiceId);
        assertThat(invoiceProduct.getProduct().getId()).isEqualTo(productId);
    }

    @Test
    void InvoiceProductRepository_CalculateTotalAmountByInvoiceId_ReturnTotalAmount() {
        // Act
        BigDecimal totalAmount = invoiceProductRepository.calculateTotalAmountByInvoiceId(invoiceId);

        // Assertion
        assertThat(totalAmount).isEqualByComparingTo(BigDecimal.valueOf(50.00));
    }
}

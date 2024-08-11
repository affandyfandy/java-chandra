package org.assignment1.midtest.repository;

import org.assignment1.midtest.criteria.InvoiceSearchCriteria;
import org.assignment1.midtest.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InvoiceRepositoryTest {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceProductRepository invoiceProductRepository;

    private static UUID customerId;
    private static UUID invoiceId;

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
        customerId = customer.getId();

        // Create a product
        Product product = Product.builder()
                .name("Product A")
                .price(BigDecimal.valueOf(10.00))
                .status(Status.ACTIVE)
                .build();
        product = productRepository.save(product);
        UUID productId = product.getId();

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
        invoiceProductRepository.save(invoiceProduct);
    }

    @AfterEach
    void tearDown() {
        invoiceProductRepository.deleteAll();
        invoiceRepository.deleteAll();
        productRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void InvoiceRepository_FindAll_ReturnAllInvoice() {
        // Act
        List<Invoice> invoices = invoiceRepository.findAll();

        // Assertion
        Assertions.assertThat(invoices).isNotEmpty();
    }

    @Test
    void InvoiceRepository_FindInvoiceById_ReturnInvoice() {
        // Act
        Invoice invoice = invoiceRepository.findInvoiceById(invoiceId);

        // Assertion
        Assertions.assertThat(invoice).isNotNull();
        Assertions.assertThat(invoice.getId()).isEqualTo(invoiceId);
    }

    @Test
    void InvoiceRepository_FindByCustomerId_ReturnInvoice() {
        // Act
        List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);

        // Assertion
        Assertions.assertThat(invoices).isNotEmpty();
        Assertions.assertThat(invoices).hasSize(1);
        Assertions.assertThat(invoices.get(0).getCustomer().getId()).isEqualTo(customerId);
    }

    @Test
    void InvoiceRepository_CalculateTotalRevenueByDateTime_ReturnTotalRevenue() {
        // Arrange
        LocalDateTime startDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime endDateTime = LocalDateTime.now().plusDays(1);

        // Act
        BigDecimal totalRevenue = invoiceRepository.calculateTotalRevenueByDateTime(startDateTime, endDateTime);

        // Assertion
        Assertions.assertThat(totalRevenue).isEqualByComparingTo(BigDecimal.valueOf(100.00));
    }

    @Test
    void testFindInvoicesByCriteria() {
        // Arrange
        InvoiceSearchCriteria criteria = new InvoiceSearchCriteria();
        criteria.setCustomerName("Customer A");
        Specification<Invoice> spec = new InvoiceSpecification(criteria);

        // Act
        List<Invoice> invoices = invoiceRepository.findAll(spec);

        // Assertion
        Assertions.assertThat(invoices).isNotEmpty();
        Assertions.assertThat(invoices.get(0).getCustomer().getName()).isEqualTo("Customer A");
        Assertions.assertThat(invoices.get(0).getInvoiceAmount().compareTo(BigDecimal.valueOf(100.0))).isZero();
    }
}


package org.assignment1.midtest.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.assignment1.midtest.dto.CustomerDTO;
import org.assignment1.midtest.dto.InvoiceDetailDTO;
import org.assignment1.midtest.dto.InvoiceProductWithoutProductIdDTO;
import org.assignment1.midtest.entity.Status;
import org.assignment1.midtest.service.impl.PdfServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class PdfServiceTest {

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private PdfServiceImpl pdfService;

    private InvoiceDetailDTO invoiceDetail;

    @BeforeEach
    void setUp() {
        // Setting up a mock CustomerDTO
        CustomerDTO customer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .phoneNumber("+621234567890")
                .status(Status.ACTIVE)
                .build();

        // Setting up mock InvoiceProductWithoutProductIdDTOs
        InvoiceProductWithoutProductIdDTO product1 = InvoiceProductWithoutProductIdDTO.builder()
                .invoiceId(UUID.randomUUID())
                .productName("Product 1")
                .price(BigDecimal.valueOf(100.0))
                .quantity(2)
                .amount(BigDecimal.valueOf(200.0))
                .build();

        InvoiceProductWithoutProductIdDTO product2 = InvoiceProductWithoutProductIdDTO.builder()
                .invoiceId(UUID.randomUUID())
                .productName("Product 2")
                .price(BigDecimal.valueOf(200.0))
                .quantity(1)
                .amount(BigDecimal.valueOf(200.0))
                .build();

        // Setting up the InvoiceDetailDTO
        invoiceDetail = InvoiceDetailDTO.builder()
                .invoiceId(UUID.randomUUID())
                .invoiceAmount(BigDecimal.valueOf(400.0))
                .invoiceDate(LocalDate.now())
                .customer(customer)
                .products(Arrays.asList(product1, product2))
                .build();

        // Mock the template processing
        when(templateEngine.process(Mockito.anyString(), Mockito.any(Context.class)))
                .thenReturn("<html><body>Mock PDF Content</body></html>");
    }

    @Test
    void testGeneratePdf() throws Exception {
        // Act
        InputStream pdfStream = pdfService.generatePdf(invoiceDetail);

        // Assert
        assertNotNull(pdfStream, "The generated PDF InputStream should not be null.");
    }
}
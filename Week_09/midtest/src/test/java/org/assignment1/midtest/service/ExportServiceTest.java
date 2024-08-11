package org.assignment1.midtest.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assignment1.midtest.entity.Customer;
import org.assignment1.midtest.entity.Invoice;
import org.assignment1.midtest.entity.InvoiceProduct;
import org.assignment1.midtest.entity.Product;
import org.assignment1.midtest.repository.InvoiceRepository;
import org.assignment1.midtest.service.impl.ExportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExportServiceTest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private ExportServiceImpl exportService;

    private UUID customerId;
    private Invoice invoice;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        Customer customer = Customer.builder().id(customerId).name("John Doe").build();

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name("Product 1")
                .price(BigDecimal.valueOf(100))
                .build();

        InvoiceProduct invoiceProduct = InvoiceProduct.builder()
                .product(product)
                .quantity(2)
                .amount(BigDecimal.valueOf(200))
                .build();

        invoice = Invoice.builder()
                .id(UUID.randomUUID())
                .customer(customer)
                .invoiceAmount(BigDecimal.valueOf(200))
                .invoiceDate(LocalDateTime.now())
                .invoiceProducts(Arrays.asList(invoiceProduct))
                .build();

        invoiceProduct.setInvoice(invoice);
    }

    @Test
    void exportInvoicesToExcel_whenInvoicesExist_shouldReturnExcelFile() throws IOException {
        // Arrange
        when(invoiceRepository.findByCustomerAndDate(customerId, 8, 2024)).thenReturn(List.of(invoice));

        // Act
        ByteArrayInputStream excelStream = exportService.exportInvoicesToExcel(customerId, 8, 2024);

        // Assert
        assertNotNull(excelStream);
        Workbook workbook = new XSSFWorkbook(excelStream);
        assertEquals(1, workbook.getNumberOfSheets());
        assertEquals("Invoices", workbook.getSheetName(0));
        workbook.close();

        verify(invoiceRepository, times(1)).findByCustomerAndDate(customerId, 8, 2024);
    }

    @Test
    void exportInvoicesToExcel_whenNoInvoicesExist_shouldThrowException() {
        // Arrange
        when(invoiceRepository.findByCustomerAndDate(customerId, 8, 2024)).thenReturn(List.of());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                exportService.exportInvoicesToExcel(customerId, 8, 2024)
        );
        assertEquals("No invoices found", exception.getMessage());

        verify(invoiceRepository, times(1)).findByCustomerAndDate(customerId, 8, 2024);
    }
}

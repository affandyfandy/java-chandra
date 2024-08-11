package org.assignment1.midtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assignment1.midtest.dto.InvoiceDTO;
import org.assignment1.midtest.dto.InvoiceDetailDTO;
import org.assignment1.midtest.dto.InvoiceListDTO;
import org.assignment1.midtest.service.ExportService;
import org.assignment1.midtest.service.InvoiceService;
import org.assignment1.midtest.service.PdfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InvoiceController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @MockBean
    private ExportService exportService;

    @MockBean
    private PdfService pdfService;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID invoiceId;
    private InvoiceDTO invoiceDTO;
    private InvoiceDetailDTO invoiceDetailDTO;
    private InvoiceListDTO invoiceListDTO;

    @BeforeEach
    void setUp() {
        invoiceId = UUID.randomUUID();
        invoiceDTO = InvoiceDTO.builder()
                .id(invoiceId)
                .customerId(UUID.randomUUID())
                .invoiceAmount(BigDecimal.valueOf(500.00))
                .invoiceDate(LocalDate.now())
                .build();

        invoiceDetailDTO = InvoiceDetailDTO.builder()
                .invoiceId(invoiceId)
                .invoiceAmount(BigDecimal.valueOf(500.00))
                .invoiceDate(LocalDate.now())
                .customer(null) // Assuming customer details are populated elsewhere
                .products(Collections.emptyList()) // Assuming product details are populated elsewhere
                .build();

        invoiceListDTO = InvoiceListDTO.builder()
                .id(invoiceId)
                .invoiceAmount(BigDecimal.valueOf(500.00))
                .customerName("John Doe")
                .invoiceDate(LocalDate.now())
                .build();
    }

    @Test
    void testGetAllInvoices() throws Exception {
        // Mocking the service call
        when(invoiceService.getAllInvoices(any(), any())).thenReturn(new PageImpl<>(Collections.singletonList(invoiceListDTO)));

        mockMvc.perform(get("/api/v1/invoices")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(invoiceId.toString()))
                .andExpect(jsonPath("$.content[0].invoiceAmount").value(500.00))
                .andExpect(jsonPath("$.content[0].customerName").value("John Doe"))
                .andExpect(jsonPath("$.content[0].invoiceDate").value(LocalDate.now().toString()));
    }

    @Test
    void testGetInvoiceDetail() throws Exception {
        when(invoiceService.getInvoiceDetail(invoiceId)).thenReturn(invoiceDetailDTO);

        mockMvc.perform(get("/api/v1/invoices/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.invoiceId").value(invoiceId.toString()))
                .andExpect(jsonPath("$.invoiceAmount").value(500.00))
                .andExpect(jsonPath("$.invoiceDate").value(LocalDate.now().toString()));
    }

    @Test
    void testAddInvoice() throws Exception {
        when(invoiceService.addInvoice(any(InvoiceDTO.class))).thenReturn(invoiceDTO);

        mockMvc.perform(post("/api/v1/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(invoiceId.toString()))
                .andExpect(jsonPath("$.invoiceAmount").value(500.00))
                .andExpect(jsonPath("$.invoiceDate").value(LocalDate.now().toString()));
    }

    @Test
    void testEditInvoice() throws Exception {
        when(invoiceService.editInvoice(any(UUID.class), any(InvoiceDTO.class))).thenReturn(invoiceDTO);

        mockMvc.perform(put("/api/v1/invoices/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(invoiceId.toString()))
                .andExpect(jsonPath("$.invoiceAmount").value(500.00))
                .andExpect(jsonPath("$.invoiceDate").value(LocalDate.now().toString()));
    }

    @Test
    void testGetInvoiceDetail_NotFound() throws Exception {
        when(invoiceService.getInvoiceDetail(invoiceId)).thenThrow(new RuntimeException("Invoice not found"));

        mockMvc.perform(get("/api/v1/invoices/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Invoice not found"));
    }

    @Test
    void testAddInvoice_BadRequest() throws Exception {
        when(invoiceService.addInvoice(any(InvoiceDTO.class))).thenThrow(new RuntimeException("Validation error"));

        mockMvc.perform(post("/api/v1/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Validation error"));
    }

    @Test
    void testEditInvoice_BadRequest() throws Exception {
        when(invoiceService.editInvoice(any(UUID.class), any(InvoiceDTO.class))).thenThrow(new RuntimeException("Validation error"));

        mockMvc.perform(put("/api/v1/invoices/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Validation error"));
    }

    @Test
    void testGenerateInvoicePdf() throws Exception {
        // Mock the service to return an InputStream
        when(invoiceService.getInvoiceDetail(invoiceId)).thenReturn(invoiceDetailDTO);
        when(pdfService.generatePdf(any(InvoiceDetailDTO.class))).thenReturn(new ByteArrayInputStream(new byte[]{1, 2, 3, 4}));

        mockMvc.perform(get("/api/v1/invoices/{id}/pdf", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=invoice.pdf"))
                .andExpect(content().contentType(MediaType.APPLICATION_PDF));
    }

    @Test
    void testGenerateInvoicePdf_InternalServerError() throws Exception {
        when(invoiceService.getInvoiceDetail(invoiceId)).thenReturn(invoiceDetailDTO);
        when(pdfService.generatePdf(any(InvoiceDetailDTO.class))).thenThrow(new RuntimeException("Failed to generate PDF"));

        mockMvc.perform(get("/api/v1/invoices/{id}/pdf", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testExportInvoicesToExcel() throws Exception {
        // Mock the service to return an InputStream for the Excel file
        when(exportService.exportInvoicesToExcel(any(), any(), any()))
                .thenReturn(new ByteArrayInputStream(new byte[]{1, 2, 3, 4}));

        mockMvc.perform(get("/api/v1/invoices/excel")
                        .param("customerId", UUID.randomUUID().toString())
                        .param("month", "7")
                        .param("year", "2023")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=invoices.xlsx"))
                .andExpect(content().contentType("application/vnd.ms-excel"));
    }

    @Test
    void testExportInvoicesToExcel_InternalServerError() throws Exception {
        // Mock the service to throw an IOException
        when(exportService.exportInvoicesToExcel(any(), any(), any()))
                .thenThrow(new IOException("Failed to export invoices"));

        mockMvc.perform(get("/api/v1/invoices/excel")
                        .param("customerId", UUID.randomUUID().toString())
                        .param("month", "7")
                        .param("year", "2023")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to export invoices to Excel"));
    }
}

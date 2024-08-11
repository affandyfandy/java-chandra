package org.assignment1.midtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assignment1.midtest.dto.InvoiceProductDTO;
import org.assignment1.midtest.service.impl.InvoiceProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceProductController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class InvoiceProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceProductServiceImpl invoiceProductService;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID invoiceId;
    private UUID productId;
    private InvoiceProductDTO invoiceProductDTO;

    @BeforeEach
    void setUp() {
        invoiceId = UUID.randomUUID();
        productId = UUID.randomUUID();
        invoiceProductDTO = InvoiceProductDTO.builder()
                .invoiceId(invoiceId)
                .productId(productId)
                .productName("Mac Book Pro")
                .quantity(5)
                .amount(BigDecimal.valueOf(100.00))
                .build();
    }

    @Test
    void testAddInvoiceProduct() throws Exception {
        when(invoiceProductService.addInvoiceProduct(any(InvoiceProductDTO.class))).thenReturn(invoiceProductDTO);

        mockMvc.perform(post("/api/v1/invoice-products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceProductDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.invoiceId").value(invoiceId.toString()))
                .andExpect(jsonPath("$.productId").value(productId.toString()))
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.amount").value(100.00));
    }

    @Test
    void testAddInvoiceProduct_withError() throws Exception {
        when(invoiceProductService.addInvoiceProduct(any(InvoiceProductDTO.class))).thenThrow(new RuntimeException("Error"));

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errors", "Error");

        mockMvc.perform(post("/api/v1/invoice-products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceProductDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Error"));
    }

    @Test
    void testEditInvoiceProduct() throws Exception {
        when(invoiceProductService.editInvoiceProduct((invoiceProductDTO), (invoiceId), (productId)))
                .thenReturn(invoiceProductDTO);

        mockMvc.perform(put("/api/v1/invoice-products/{invoiceId}/{productId}", invoiceId, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceProductDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.invoiceId").value(invoiceId.toString()))
                .andExpect(jsonPath("$.productId").value(productId.toString()))
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.amount").value(100.00));
    }

    @Test
    void testEditInvoiceProduct_withError() throws Exception {
        when(invoiceProductService.editInvoiceProduct((invoiceProductDTO), (invoiceId), (productId)))
                .thenThrow(new RuntimeException("Error"));

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errors", "Error");

        mockMvc.perform(put("/api/v1/invoice-products/{invoiceId}/{productId}", invoiceId, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceProductDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Error"));
    }

    @Test
    void testDeleteInvoiceProduct() throws Exception {
        mockMvc.perform(delete("/api/v1/invoice-products/{invoiceId}/{productId}", invoiceId, productId))
                .andExpect(status().isOk());
    }

}

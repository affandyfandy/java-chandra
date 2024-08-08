package org.assignment1.midtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assignment1.midtest.dto.ProductDTO;
import org.assignment1.midtest.entity.Status;
import org.assignment1.midtest.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID productId;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        productDTO = ProductDTO.builder()
                .id(productId)
                .name("Mac Book")
                .price(BigDecimal.valueOf(899.00))
                .status(Status.ACTIVE)
                .build();
    }


    @Test
    void ProductController_getAllProducts_ReturnsAllProduct() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductDTO> page = new PageImpl<>(Collections.singletonList(productDTO), pageable, 1);

        when(productService.getAllProducts(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void ProductController_createProduct_WithValidationError() throws Exception {
        ProductDTO invalidProductDTO = ProductDTO.builder()
                .id(productId)
                .price(BigDecimal.valueOf(899.00))
                .status(Status.ACTIVE)
                .build();

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProductDTO)))
                .andExpect(status().isBadRequest());

        verify(productService, times(0)).createProduct(any(ProductDTO.class));
    }

    @Test
    void ProductController_createProduct_ReturnsCreatedProduct() throws Exception {
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mac Book"))
                .andExpect(jsonPath("$.price").value(899.00))
                .andExpect(jsonPath("$.status").value("ACTIVE"));

        verify(productService, times(1)).createProduct(any(ProductDTO.class));
    }

    @Test
    void ProductController_getProductById_ReturnsProduct() throws Exception {
        when(productService.getProductById(productId)).thenReturn(Optional.of(productDTO));

        mockMvc.perform(get("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mac Book"));
    }

    @Test
    void ProductController_updateProduct_ReturnUpdatesProduct() throws Exception {
        when(productService.updateProduct(eq(productId), any(ProductDTO.class))).thenReturn(Optional.of(productDTO));

        mockMvc.perform(put("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mac Book"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void ProductController_toggleProductStatus_ReturnUpdatesProductStatus() throws Exception {
        when(productService.toggleProductStatus(productId)).thenReturn(Optional.of(productDTO));

        mockMvc.perform(patch("/api/v1/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void ProductController_deleteProduct_ReturnDeletedProduct() throws Exception {
        doNothing().when(productService).deleteProduct(productId);
        mockMvc.perform(delete("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Product successfully deleted"));

        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void ProductController_importProductsFromCsv_Success() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "name,price,status\nMac Book,899.00,ACTIVE".getBytes());

        mockMvc.perform(multipart("/api/v1/products/import").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("Products imported successfully"));

        verify(productService, times(1)).importProductsFromCsv(any(MultipartFile.class));
    }

    @Test
    void ProductController_importProductsFromCsv_Failure() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "invalid,data".getBytes());

        doThrow(new IOException("Failed to import products")).when(productService).importProductsFromCsv(any(MultipartFile.class));

        mockMvc.perform(multipart("/api/v1/products/import").file(file))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to import products"));

        verify(productService, times(1)).importProductsFromCsv(any(MultipartFile.class));
    }

    @Test
    void ProductController_searchProducts_ReturnsSearchProducts() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.by("name").with(Sort.Direction.ASC)));
        Page<ProductDTO> page = new PageImpl<>(Collections.singletonList(productDTO), pageable, 1);

        when(productService.searchProducts(("Mac Book"), (Status.ACTIVE), (pageable))).thenReturn(page);

        mockMvc.perform(get("/api/v1/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Mac Book")
                        .param("status", "ACTIVE")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "name,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Mac Book"))
                .andExpect(jsonPath("$.content[0].status").value("ACTIVE"));
    }
}


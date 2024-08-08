package org.assignment1.midtest.service;

import org.assignment1.midtest.dto.InvoiceProductDTO;
import org.assignment1.midtest.entity.*;
import org.assignment1.midtest.mapper.InvoiceProductMapper;
import org.assignment1.midtest.repository.InvoiceProductRepository;
import org.assignment1.midtest.repository.InvoiceRepository;
import org.assignment1.midtest.repository.ProductRepository;
import org.assignment1.midtest.service.impl.InvoiceProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceProductServiceTest {
    @InjectMocks
    private InvoiceProductServiceImpl invoiceProductService;

    @Mock
    private InvoiceProductRepository invoiceProductRepository;

    @Mock
    private InvoiceProductMapper invoiceProductMapper;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private ProductRepository productRepository;

    private UUID invoiceId;
    private UUID productId;
    private InvoiceProductDTO invoiceProductDTO;
    private Invoice invoice;
    private InvoiceProduct invoiceProduct;
    private Product product;

    @BeforeEach
    void setUp() {
        // Initialize UUIDs
        invoiceId = UUID.randomUUID();
        productId = UUID.randomUUID();

        // Initialize DTO
        invoiceProductDTO = InvoiceProductDTO.builder().invoiceId(invoiceId).productId(productId).productName("Mac").quantity(5).amount(BigDecimal.valueOf(50)).build();

        // Initialize Invoice
        invoice = Invoice.builder().id(invoiceId).invoiceAmount(BigDecimal.ZERO).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();

        // Initialize Product
        product = Product.builder().id(productId).name("Mac").price(BigDecimal.valueOf(10)).status(Status.ACTIVE).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();

        // Initialize InvoiceProduct
        invoiceProduct = InvoiceProduct.builder().invoice(invoice).product(product).productName("Mac").quantity(5).amount(BigDecimal.valueOf(50)).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
    }

    @Test
    void InvoiceProduct_GetAllInvoiceProducts_ReturnsAllInvoiceProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<InvoiceProduct> page = new PageImpl<>(Collections.singletonList(invoiceProduct), pageable, 1);

        when(invoiceProductRepository.findAll(pageable)).thenReturn(page);
        when(invoiceProductMapper.toInvoiceProductDTO(any(InvoiceProduct.class))).thenReturn(invoiceProductDTO);

        Page<InvoiceProductDTO> result = invoiceProductService.getAllInvoiceProducts(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(invoiceProductRepository, times(1)).findAll(pageable);
    }

    @Test
    void InvoiceProduct_AddInvoiceProduct_ReturnsAddedProduct() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceProductRepository.findById(new InvoiceProductId(invoiceId, productId)))
                .thenReturn(Optional.empty());
        when(invoiceProductMapper.toInvoiceProduct(invoiceProductDTO, invoice, product))
                .thenReturn(invoiceProduct);
        when(invoiceProductRepository.save(any(InvoiceProduct.class)))
                .thenReturn(invoiceProduct);
        when(invoiceProductMapper.toInvoiceProductDTO(any(InvoiceProduct.class)))
                .thenReturn(invoiceProductDTO);
        when(invoiceProductRepository.calculateTotalAmountByInvoiceId(invoiceId))
                .thenReturn(BigDecimal.valueOf(50));

        InvoiceProductDTO result = invoiceProductService.addInvoiceProduct(invoiceProductDTO);

        assertNotNull(result);
        assertEquals(invoiceProductDTO, result);
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(productRepository, times(1)).findById(productId);
        verify(invoiceProductRepository, times(1)).save(invoiceProduct);
    }

    @Test
    void InvoiceProduct_EditInvoiceProduct_ReturnsUpdatedProduct() {
        when(invoiceProductRepository.findById(new InvoiceProductId(invoiceId, productId)))
                .thenReturn(Optional.of(invoiceProduct));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceProductRepository.save(any(InvoiceProduct.class))).thenReturn(invoiceProduct);
        when(invoiceProductMapper.toInvoiceProductDTO(any(InvoiceProduct.class))).thenReturn(invoiceProductDTO);
        when(invoiceProductRepository.calculateTotalAmountByInvoiceId(invoiceId))
                .thenReturn(BigDecimal.valueOf(50));

        InvoiceProductDTO result = invoiceProductService.editInvoiceProduct(invoiceProductDTO, invoiceId, productId);

        assertNotNull(result);
        assertEquals(invoiceProductDTO, result);
        verify(invoiceProductRepository, times(1)).findById(new InvoiceProductId(invoiceId, productId));
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(productRepository, times(1)).findById(productId);
        verify(invoiceProductRepository, times(1)).save(invoiceProduct);
    }

    @Test
    void InvoiceProduct_DeleteInvoiceProduct_ReturnsDeletedProduct() {
        when(invoiceProductRepository.findById(new InvoiceProductId(invoiceId, productId)))
                .thenReturn(Optional.of(invoiceProduct));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        invoiceProductService.deleteInvoiceProduct(invoiceId, productId);

        verify(invoiceProductRepository, times(1)).findById(new InvoiceProductId(invoiceId, productId));
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(invoiceProductRepository, times(1)).deleteById(new InvoiceProductId(invoiceId, productId));
    }
}


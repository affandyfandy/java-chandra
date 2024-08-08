package org.assignment1.midtest.service;

import org.assignment1.midtest.criteria.InvoiceSearchCriteria;
import org.assignment1.midtest.dto.InvoiceDTO;
import org.assignment1.midtest.dto.InvoiceDetailDTO;
import org.assignment1.midtest.dto.InvoiceListDTO;
import org.assignment1.midtest.entity.Customer;
import org.assignment1.midtest.entity.Invoice;
import org.assignment1.midtest.entity.Status;
import org.assignment1.midtest.mapper.InvoiceMapper;
import org.assignment1.midtest.repository.CustomerRepository;
import org.assignment1.midtest.repository.InvoiceRepository;
import org.assignment1.midtest.repository.InvoiceSpecification;
import org.assignment1.midtest.service.impl.InvoiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    private UUID invoiceId;
    private UUID customerId;
    private Invoice invoice;
    private InvoiceDTO invoiceDTO;
    private InvoiceListDTO invoiceListDTO;
    private InvoiceDetailDTO invoiceDetailDTO;
    private Customer customer;

    @BeforeEach
    void setUp() {
        invoiceId = UUID.randomUUID();
        customerId = UUID.randomUUID();

        invoiceDTO = InvoiceDTO.builder()
                .customerId(customerId)
                .invoiceDate(LocalDate.now())
                .build();

        invoiceListDTO = InvoiceListDTO.builder()
                .customerName("Ryan Chandra")
                .build();

        invoiceDetailDTO = InvoiceDetailDTO.builder()
                .invoiceId(invoiceId)
                .build();

        invoice = Invoice.builder()
                .id(invoiceId)
                .invoiceDate(LocalDateTime.now())
                .invoiceAmount(BigDecimal.ZERO)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();

        customer = Customer.builder()
                .id(customerId)
                .name("Ryan Chandra")
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void getAllInvoices_ReturnsAllInvoices() {
        Pageable pageable = PageRequest.of(0, 10);
        InvoiceSearchCriteria criteria = new InvoiceSearchCriteria();
        Page<Invoice> page = new PageImpl<>(Collections.singletonList(invoice), pageable, 1);
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(criteria);

        when(invoiceRepository.findAll(any(InvoiceSpecification.class), eq(pageable))).thenReturn(page);
        when(invoiceMapper.toInvoiceListDTO(any(Invoice.class))).thenReturn(invoiceListDTO);

        Page<InvoiceListDTO> result = invoiceService.getAllInvoices(pageable, criteria);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(invoiceRepository, times(1)).findAll(any(InvoiceSpecification.class), eq(pageable));
    }

    @Test
    void getInvoiceById_ReturnsInvoice() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceMapper.toInvoicesDTO(any(Invoice.class))).thenReturn(invoiceDTO);

        InvoiceDTO result = invoiceService.getInvoiceById(invoiceId);

        assertNotNull(result);
        assertEquals(invoiceDTO, result);
        verify(invoiceRepository, times(1)).findById(invoiceId);
    }

    @Test
    void addInvoice_ReturnsAddedInvoice() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(invoiceMapper.toInvoices(any(InvoiceDTO.class))).thenReturn(invoice);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(invoiceMapper.toInvoicesDTO(any(Invoice.class))).thenReturn(invoiceDTO);

        InvoiceDTO result = invoiceService.addInvoice(invoiceDTO);

        assertNotNull(result);
        assertEquals(invoiceDTO, result);
        verify(customerRepository, times(1)).findById(customerId);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void editInvoice_ReturnsEditedInvoice() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(invoiceMapper.toInvoicesDTO(any(Invoice.class))).thenReturn(invoiceDTO);

        InvoiceDTO result = invoiceService.editInvoice(invoiceId, invoiceDTO);

        assertNotNull(result);
        assertEquals(invoiceDTO, result);
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(customerRepository, times(1)).findById(customerId);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void getInvoiceDetail_ReturnsInvoiceDetail() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceMapper.toInvoiceDetailDTO(any(Invoice.class))).thenReturn(invoiceDetailDTO);

        InvoiceDetailDTO result = invoiceService.getInvoiceDetail(invoiceId);

        assertNotNull(result);
        assertEquals(invoiceDetailDTO, result);
        verify(invoiceRepository, times(1)).findById(invoiceId);
    }
}


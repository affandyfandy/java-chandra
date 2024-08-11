package org.assignment1.midtest.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.assignment1.midtest.criteria.InvoiceSearchCriteria;
import org.assignment1.midtest.dto.InvoiceDTO;
import org.assignment1.midtest.dto.InvoiceDetailDTO;
import org.assignment1.midtest.dto.InvoiceListDTO;
import org.assignment1.midtest.entity.Customer;
import org.assignment1.midtest.entity.Invoice;
import org.assignment1.midtest.mapper.InvoiceMapper;
import org.assignment1.midtest.repository.CustomerRepository;
import org.assignment1.midtest.repository.InvoiceRepository;
import org.assignment1.midtest.repository.InvoiceSpecification;
import org.assignment1.midtest.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper,
                              CustomerRepository customerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<InvoiceListDTO> getAllInvoices(Pageable pageable, InvoiceSearchCriteria criteria) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(criteria);
        return invoiceRepository.findAll(invoiceSpecification, pageable).map(invoiceMapper::toInvoiceListDTO);
    }

    @Override
    public InvoiceDTO getInvoiceById(UUID id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
        return invoiceMapper.toInvoicesDTO(invoice);
    }

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        UUID customerId = invoiceDTO.getCustomerId();
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }

        Customer customer = customerOpt.get();

        if (customer.getStatus().name().equals("INACTIVE")) {
            throw new IllegalArgumentException("Customer is inactive");
        }

        Invoice invoice = invoiceMapper.toInvoices(invoiceDTO);

        LocalDate invoiceDateOnly = invoiceDTO.getInvoiceDate();
        invoice.setInvoiceDate(LocalDateTime.of(invoiceDateOnly, LocalTime.MIDNIGHT));

        if (invoice.getInvoiceAmount() == null) {
            invoice.setInvoiceAmount(BigDecimal.ZERO);
        }

        invoice.setCustomer(customer);
        invoice.setCreatedTime(LocalDateTime.now());
        invoice.setUpdatedTime(LocalDateTime.now());
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toInvoicesDTO(invoice);
    }

    @Override
    public InvoiceDTO editInvoice(UUID id, InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));

        // Throw exception if invoice is older than 10 minutes
        if (invoice.getCreatedTime().plusMinutes(10).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invoice cannot be edited after 10 minutes");
        }

        Customer customer = customerRepository.findById(invoiceDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        if (customer.getStatus().name().equals("INACTIVE")) {
            throw new IllegalArgumentException("Customer is inactive");
        }

        invoice.setCustomer(customer);
        invoice.setInvoiceDate(LocalDateTime.of(invoiceDTO.getInvoiceDate(), LocalTime.MIDNIGHT));

        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toInvoicesDTO(invoice);
    }

    @Override
    public InvoiceDetailDTO getInvoiceDetail(UUID invoiceId) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(invoiceId);
        if (invoiceOpt.isPresent()) {
            return invoiceMapper.toInvoiceDetailDTO(invoiceOpt.get());
        } else {
            throw new RuntimeException("Invoice not found");
        }
    }
}

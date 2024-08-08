package org.assignment1.midtest.service;

import java.util.UUID;

import org.assignment1.midtest.criteria.InvoiceSearchCriteria;
import org.assignment1.midtest.dto.InvoiceDTO;
import org.assignment1.midtest.dto.InvoiceDetailDTO;
import org.assignment1.midtest.dto.InvoiceListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface InvoiceService {
    Page<InvoiceListDTO> getAllInvoices(Pageable pageable, InvoiceSearchCriteria criteria);

    InvoiceDTO getInvoiceById(UUID id);

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    InvoiceDTO editInvoice(UUID id, InvoiceDTO invoiceDTO);

    InvoiceDetailDTO getInvoiceDetail(UUID invoiceId);

}
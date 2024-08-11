package org.assignment1.midtest.mapper;

import org.assignment1.midtest.dto.InvoiceDTO;
import org.assignment1.midtest.dto.InvoiceDetailDTO;
import org.assignment1.midtest.dto.InvoiceListDTO;
import org.assignment1.midtest.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class, InvoiceProductMapper.class })
public interface InvoiceMapper {

    @Mapping(source = "customer.name", target = "customerName")
    InvoiceListDTO toInvoiceListDTO(Invoice invoice);

    // Invoice Detail Mapper here
    // to do

    Invoice toInvoice(InvoiceListDTO invoiceDTO);

    // For invoice
    @Mapping(source = "customer.id", target = "customerId")
    InvoiceDTO toInvoicesDTO(Invoice invoice);

    // Invoice Detail Mapper here
    // to do

    Invoice toInvoices(InvoiceDTO invoiceDTO);

    @Mapping(source = "invoice.id", target = "invoiceId")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "invoiceProducts", target = "products")
    InvoiceDetailDTO toInvoiceDetailDTO(Invoice invoice);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "products", target = "invoiceProducts")
    Invoice toInvoice(InvoiceDetailDTO invoiceDetailDTO);
}

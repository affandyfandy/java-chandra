package org.assignment1.midtest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetailDTO {

    private UUID invoiceId;
    private BigDecimal invoiceAmount;
    private LocalDate invoiceDate;
    private CustomerDTO customer;
    private List<InvoiceProductWithoutProductIdDTO> products;
}
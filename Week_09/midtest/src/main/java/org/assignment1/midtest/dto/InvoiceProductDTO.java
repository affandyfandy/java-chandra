package org.assignment1.midtest.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceProductDTO {
    private UUID invoiceId;
    private UUID productId;
    private String productName;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    private BigDecimal amount;
}
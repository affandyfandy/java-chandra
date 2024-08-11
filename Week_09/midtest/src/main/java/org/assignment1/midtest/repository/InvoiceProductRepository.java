package org.assignment1.midtest.repository;

import org.assignment1.midtest.entity.InvoiceProduct;
import org.assignment1.midtest.entity.InvoiceProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, InvoiceProductId> {
    @Query("SELECT SUM(ip.amount) FROM InvoiceProduct ip WHERE ip.invoice.id = :invoiceId")
    BigDecimal calculateTotalAmountByInvoiceId(UUID invoiceId);
}

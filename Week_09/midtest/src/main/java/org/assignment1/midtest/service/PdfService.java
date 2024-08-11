package org.assignment1.midtest.service;

import org.assignment1.midtest.dto.InvoiceDetailDTO;
import java.io.InputStream;


public interface PdfService {
    InputStream generatePdf(InvoiceDetailDTO invoiceDetail) throws Exception;
}

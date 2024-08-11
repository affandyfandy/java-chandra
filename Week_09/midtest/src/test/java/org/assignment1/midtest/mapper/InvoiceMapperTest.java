package org.assignment1.midtest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.assignment1.midtest.dto.InvoiceDTO;
import org.assignment1.midtest.dto.InvoiceListDTO;
import org.assignment1.midtest.entity.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class InvoiceMapperTest {

    @InjectMocks
    private InvoiceMapperImpl invoiceMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toInvoiceListDTO_ShouldMapCorrectly() throws Exception {
        // Arrange
        UUID invoiceId = UUID.randomUUID();
        LocalDateTime invoiceDate = LocalDateTime.now();
        Invoice invoice = Invoice.builder()
                .id(invoiceId)
                .invoiceAmount(BigDecimal.valueOf(150))
                .invoiceDate(invoiceDate)
                .build();

        XMLGregorianCalendar xgcDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(invoiceDate.toString());

        // Act
        InvoiceListDTO result = invoiceMapper.toInvoiceListDTO(invoice);

        // Assert
        assertNotNull(result);
        assertEquals(invoiceId, result.getId());
        assertEquals(BigDecimal.valueOf(150), result.getInvoiceAmount());
        assertEquals(xmlGregorianCalendarToLocalDate(xgcDate), result.getInvoiceDate());
    }

    @Test
    void toInvoicesDTO_ShouldMapCorrectly() throws Exception {
        // Arrange
        UUID invoiceId = UUID.randomUUID();
        LocalDateTime invoiceDate = LocalDateTime.now();
        Invoice invoice = Invoice.builder()
                .id(invoiceId)
                .invoiceAmount(BigDecimal.valueOf(200))
                .invoiceDate(invoiceDate)
                .build();

        XMLGregorianCalendar xgcDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(invoiceDate.toString());

        // Act
        InvoiceDTO result = invoiceMapper.toInvoicesDTO(invoice);

        // Assert
        assertNotNull(result);
        assertEquals(invoiceId, result.getId());
        assertEquals(BigDecimal.valueOf(200), result.getInvoiceAmount());
        assertEquals(xmlGregorianCalendarToLocalDate(xgcDate), result.getInvoiceDate());
    }
    private static LocalDate xmlGregorianCalendarToLocalDate(XMLGregorianCalendar xcal) {
        if (xcal == null) {
            return null;
        }
        return LocalDate.of(xcal.getYear(), xcal.getMonth(), xcal.getDay());
    }


}

package org.assignment1.midtest.service;

import org.assertj.core.api.Assertions;
import org.assignment1.midtest.dto.RevenueDTO;
import org.assignment1.midtest.repository.InvoiceRepository;
import org.assignment1.midtest.service.impl.RevenueServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RevenueServiceTest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private RevenueServiceImpl revenueService;

    @Test
    void RevenueService_getRevenueByDay_ReturnsCorrectRevenue() {
        // Arrange
        LocalDate date = LocalDate.of(2023, 8, 1);
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(LocalTime.MAX);
        BigDecimal expectedRevenue = BigDecimal.valueOf(1000);

        when(invoiceRepository.calculateTotalRevenueByDateTime(startDateTime, endDateTime)).thenReturn(expectedRevenue);

        // Act
        RevenueDTO result = revenueService.getRevenueByDay(date);

        // Assertion
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPeriod()).isEqualTo(date.toString());
        Assertions.assertThat(result.getTotalRevenue()).isEqualByComparingTo(expectedRevenue);
    }

    @Test
    void RevenueService_getRevenueByMonth_ReturnsCorrectRevenue() {
        // Arrange
        int year = 2023;
        int month = 8;
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        BigDecimal expectedRevenue = BigDecimal.valueOf(30000);

        when(invoiceRepository.calculateTotalRevenueByDateTime(startDateTime, endDateTime)).thenReturn(expectedRevenue);

        // Act
        RevenueDTO result = revenueService.getRevenueByMonth(year, month);

        // Assertion
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPeriod()).isEqualTo(startDate.getMonth().toString() + " " + year);
        Assertions.assertThat(result.getTotalRevenue()).isEqualByComparingTo(expectedRevenue);
    }

    @Test
    void RevenueService_getRevenueByYear_ReturnsCorrectRevenue() {
        // Arrange
        int year = 2023;
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        BigDecimal expectedRevenue = BigDecimal.valueOf(365000);

        when(invoiceRepository.calculateTotalRevenueByDateTime(startDateTime, endDateTime)).thenReturn(expectedRevenue);

        // Act
        RevenueDTO result = revenueService.getRevenueByYear(year);

        // Assertion
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPeriod()).isEqualTo(String.valueOf(year));
        Assertions.assertThat(result.getTotalRevenue()).isEqualByComparingTo(expectedRevenue);
    }
}


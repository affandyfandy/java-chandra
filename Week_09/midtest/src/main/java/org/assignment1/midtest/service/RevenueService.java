package org.assignment1.midtest.service;

import org.assignment1.midtest.dto.RevenueDTO;

import java.time.LocalDate;

public interface RevenueService {
    RevenueDTO getRevenueByDay(LocalDate date);
    RevenueDTO getRevenueByMonth(int year, int month);
    RevenueDTO getRevenueByYear(int year);

}

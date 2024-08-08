package org.assignment1.midtest.controller;

import org.assignment1.midtest.dto.RevenueDTO;
import org.assignment1.midtest.service.impl.RevenueServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RevenueController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class RevenueControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RevenueServiceImpl revenueService;

    @InjectMocks
    private RevenueController revenueController;

    private RevenueDTO revenueDTO;

    @BeforeEach
    void setUp() {
        revenueDTO = new RevenueDTO();
        revenueDTO.setPeriod("2023-08-01");
        revenueDTO.setTotalRevenue(BigDecimal.valueOf(1000.00));
    }

    @Test
    void RevenueController_getRevenueByDay_ReturnsRevenue() throws Exception {
        LocalDate date = LocalDate.of(2023, 8, 1);
        when(revenueService.getRevenueByDay((date))).thenReturn(revenueDTO);

        mockMvc.perform(get("/api/v1/revenue/day")
                        .param("date", "2023-08-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.period").value("2023-08-01"))
                .andExpect(jsonPath("$.totalRevenue").value(1000.00));
    }

    @Test
    void RevenueController_getRevenueByMonth_ReturnsRevenue() throws Exception {
        when(revenueService.getRevenueByMonth(anyInt(), anyInt())).thenReturn(revenueDTO);

        mockMvc.perform(get("/api/v1/revenue/month")
                        .param("year", "2023")
                        .param("month", "8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.period").value("2023-08-01"))
                .andExpect(jsonPath("$.totalRevenue").value(1000.00));
    }

    @Test
    void RevenueController_getRevenueByYear_ReturnsRevenue() throws Exception {
        when(revenueService.getRevenueByYear(anyInt())).thenReturn(revenueDTO);

        mockMvc.perform(get("/api/v1/revenue/year")
                        .param("year", "2023")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.period").value("2023-08-01"))
                .andExpect(jsonPath("$.totalRevenue").value(1000.00));
    }
}

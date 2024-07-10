package com.assignment2.assignment2.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class dateUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }
}

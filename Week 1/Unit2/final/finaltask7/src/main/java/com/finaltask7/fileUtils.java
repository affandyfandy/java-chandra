package com.finaltask7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class fileUtils {
    public static void readCSVOpenCsv(String csvFile, List<Employee> employees) {
        int count = 0;
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            employees.clear(); // Clear existing data
            String[] line;
            while ((line = reader.readNext()) != null) {
                try {
                    LocalDate dateOfBirth = dateUtils.parseDate(line[2]);
                    Employee employee = new Employee(line[0], line[1], dateOfBirth, line[3], line[4]);
                    employees.add(employee);
                    count++;
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\n" + count + " Data successfully added...");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public static void readCSVManual(String csvFile, List<Employee> employees) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            employees.clear(); // Clear existing data
            while ((line = br.readLine()) != null) {
                line = line.trim().replaceAll("^\"|\"$", "");
                String[] data = line.split(",");
                try {
                    LocalDate dateOfBirth = dateUtils.parseDate(data[2].replaceAll("^\"|\"$", ""));
                    Employee employee = new Employee(data[0], data[1], dateOfBirth, data[3], data[4]);
                    employees.add(employee);
                    count++;
                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    // Handle parsing errors or missing data as needed
                }
            }
            System.out.println("\n" + count + " Data successfully added...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCSVOpen(List<Employee> employees, String fileName) {
        try (CSVWriter export = new CSVWriter(new FileWriter(fileName))) {
            for (Employee employee : employees) {
                export.writeNext(
                        new String[] { employee.getID(), employee.getName(),
                                dateUtils.formatDate(employee.getDateBirth()),
                                employee.getAddress(), employee.getDepartment() });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.assignment1.assignment2.utils;

import org.assignment1.assignment2.model.Employee;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Employee> csvToEmployees(InputStream is) throws CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List<Employee> employees = new ArrayList<>();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                Employee employee = new Employee();
                employee.setId(nextRecord[0].trim());
                employee.setFirstName(nextRecord[1].trim());
                employee.setLastName(nextRecord[2].trim());
                LocalDate dob = DateUtils.parseDate(nextRecord[3].trim().replaceAll("^\"|\"$", ""));
                employee.setDob(dob);
                employee.setAddress(nextRecord[4].trim());
                employee.setDepartment(nextRecord[5].trim());
                employee.setSalary(Integer.parseInt(nextRecord[6].trim()));
                employee.setEmail(nextRecord[7].trim());
                employees.add(employee);
            }
            return employees;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}

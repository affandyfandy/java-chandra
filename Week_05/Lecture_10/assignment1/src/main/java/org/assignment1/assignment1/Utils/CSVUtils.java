package org.assignment1.assignment1.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.assignment1.assignment1.dto.EmployeeDTO;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CSVUtils {

    public List<EmployeeDTO> parseCSVFile(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                @SuppressWarnings("deprecation")
                CSVParser csvParser = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim()
                        .parse(fileReader)) {

            List<EmployeeDTO> employeeDTOs = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(UUID.fromString(csvRecord.get("id")));
                employeeDTO.setName(csvRecord.get("name"));
                employeeDTO.setDob(DateUtils.parseDate(csvRecord.get("dob")));
                employeeDTO.setAddress(csvRecord.get("address"));
                employeeDTO.setDepartment(csvRecord.get("department"));
                employeeDTO.setEmail(csvRecord.get("email"));
                employeeDTO.setNphone(csvRecord.get("nphone"));
                employeeDTOs.add(employeeDTO);
            }

            return employeeDTOs;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

}

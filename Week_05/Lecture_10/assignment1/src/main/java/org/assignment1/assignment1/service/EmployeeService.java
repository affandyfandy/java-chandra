package org.assignment1.assignment1.service;

import org.assignment1.assignment1.dto.EmployeeDTO;
import org.assignment1.assignment1.model.Employee;
import org.assignment1.assignment1.repository.EmployeeRepository;
import org.assignment1.assignment1.mapper.EmployeeMapper;
import org.assignment1.assignment1.utils.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    @Autowired
    private CSVUtils csvUtils;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::employeeToEmployeeDTO);
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setDob(employeeDTO.getDob());
        employee.setAddress(employeeDTO.getAddress());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setEmail(employeeDTO.getEmail());
        employee.setNphone(employeeDTO.getNphone());
        return employeeRepository.save(employee);
    }

    public EmployeeDTO updateEmployee(UUID id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employeeMapper.updateEmployeeFromDTO(employeeDTO, employee);
            employee.setId(id);
            employee = employeeRepository.save(employee);
            return employeeMapper.employeeToEmployeeDTO(employee);
        }
        throw new IllegalArgumentException("Employee not found with id: " + id);
    }

    public void deleteEmployee(UUID id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        employeeOpt.ifPresent(employeeRepository::delete);
    }

    public void uploadEmployees(MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            List<EmployeeDTO> employeeDTOs = csvUtils.parseCSVFile(is);
            List<Employee> employees = employeeDTOs.stream()
                    .map(dto -> {
                        Employee employee = employeeMapper.employeeDTOToEmployee(dto);
                        employee.setId(dto.getId()); // Set the ID manually
                        return employee;
                    })
                    .collect(Collectors.toList());
            saveAllEmployees(employees);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload CSV file: " + e.getMessage());
        }
    }

    public void saveAllEmployees(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    public List<EmployeeDTO> getEmployeesByDepartment(String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        return employees.stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }
}

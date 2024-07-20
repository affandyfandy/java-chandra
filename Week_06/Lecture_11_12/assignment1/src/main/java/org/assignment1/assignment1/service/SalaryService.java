package org.assignment1.assignment1.service;

import org.assignment1.assignment1.dto.SalaryDTO;
import org.assignment1.assignment1.mapper.SalaryMapper;
import org.assignment1.assignment1.entity.Employee;
import org.assignment1.assignment1.entity.Salary;
import org.assignment1.assignment1.repository.EmployeeRepository;
import org.assignment1.assignment1.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final SalaryMapper salaryMapper;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public SalaryService(SalaryRepository salaryRepository, SalaryMapper salaryMapper,
            EmployeeRepository employeeRepository) {

        this.salaryRepository = salaryRepository;
        this.salaryMapper = salaryMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<SalaryDTO> getAllSalaries() {
        List<Salary> salaries = salaryRepository.findAll();
        return salaries.stream()
                .map(salaryMapper::toDTO)
                .toList();
    }

    public SalaryDTO saveSalary(SalaryDTO salaryDTO) {
        Employee employee = employeeRepository.findById(salaryDTO.getEmpNo())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Salary salary = salaryMapper.toEntity(salaryDTO);
        salary.setEmployees(employee);

        Salary savedSalary = salaryRepository.save(salary);
        return salaryMapper.toDTO(savedSalary);
    }

    public SalaryDTO updateSalary(SalaryDTO salaryDTO) {
        // Validate that the employee exists
        Employee employee = employeeRepository.findById(salaryDTO.getEmpNo())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Convert DTO to entity
        Salary salary = salaryMapper.toEntity(salaryDTO);
        salary.setEmployees(employee);

        // Save the entity
        Salary updatedSalary = salaryRepository.save(salary);
        return salaryMapper.toDTO(updatedSalary);
    }
}

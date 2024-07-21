package org.assignment1.assignment3.service.Impl;

import lombok.AllArgsConstructor;
import org.assignment1.assignment3.model.Employee;
import org.assignment1.assignment3.repository.EmployeeRepository;
import org.assignment1.assignment3.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(String theId) {
        return employeeRepository.findById(theId).orElseThrow();
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(String theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    @Override
    public Optional<Employee> getHighestSalaryEmployee() {
        return employeeRepository.findAll().stream().max(Comparator.comparingDouble(Employee::getSalary));
    }

    @Override
    public Optional<Employee> getLowestSalaryEmployee() {
        return employeeRepository.findAll().stream().min(Comparator.comparingDouble(Employee::getSalary));
    }

    @Override
    public long getRecordCount() {
        return employeeRepository.count();
    }

    @Override
    public OptionalDouble getAverageSalary() {
        return employeeRepository.findAll().stream().mapToDouble(Employee::getSalary).average();
    }

}

package org.assignment1.assignment3.service;

import org.assignment1.assignment3.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(String id);

    void save(Employee employee);

    void deleteById(String id);

    void saveAll(List<Employee> employees);

    Optional<Employee> getHighestSalaryEmployee();

    Optional<Employee> getLowestSalaryEmployee();

    long getRecordCount();

    OptionalDouble getAverageSalary();

}

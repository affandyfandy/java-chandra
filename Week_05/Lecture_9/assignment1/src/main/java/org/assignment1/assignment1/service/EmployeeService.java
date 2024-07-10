package org.assignment1.assignment1.service;

import org.assignment1.assignment1.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(String id);

    void save(Employee employee);

    void deleteById(String id);
}

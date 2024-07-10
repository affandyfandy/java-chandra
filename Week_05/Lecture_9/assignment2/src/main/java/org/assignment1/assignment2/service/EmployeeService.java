package org.assignment1.assignment2.service;

import org.assignment1.assignment2.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(String id);

    void save(Employee employee);

    void deleteById(String id);

    void saveAll(List<Employee> employees);
}

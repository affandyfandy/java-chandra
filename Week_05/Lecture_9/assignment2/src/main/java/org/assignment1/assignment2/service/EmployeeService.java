package org.assignment1.assignment2.service;

import org.assignment1.assignment2.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);

    void saveAll(List<Employee> employees);
}

package org.assignment1.assignment2.service;

import org.assignment1.assignment2.model.Employee;
import org.assignment1.assignment2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.getAllEmployees();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepo.getEmployeeById(id);
    }

    public int addEmployee(Employee employee) {
        return employeeRepo.addEmployee(employee);
    }

    public int updateEmployee(Employee employee) {
        return employeeRepo.updateEmployee(employee);
    }

    public int deleteById(int id) {
        return employeeRepo.deleteById(id);
    }
}

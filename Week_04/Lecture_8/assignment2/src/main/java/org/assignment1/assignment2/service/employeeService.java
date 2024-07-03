package org.assignment1.assignment2.service;

import org.assignment1.assignment2.model.employee;
import org.assignment1.assignment2.repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class employeeService {

    @Autowired
    private employeeRepository employeeRepo;

    public List<employee> getAllEmployees() {
        return employeeRepo.getAllEmployees();
    }

    public employee getEmployeeById(int id) {
        return employeeRepo.getEmployeeById(id);
    }

    public int addEmployee(employee employee) {
        return employeeRepo.addEmployee(employee);
    }

    public int updateEmployee(employee employee) {
        return employeeRepo.updateEmployee(employee);
    }

    public int deleteById(int id) {
        return employeeRepo.deleteById(id);
    }
}

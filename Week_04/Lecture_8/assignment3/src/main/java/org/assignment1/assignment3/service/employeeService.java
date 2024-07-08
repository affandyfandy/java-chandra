package org.assignment1.assignment3.service;

import org.assignment1.assignment3.model.Employee;
import org.assignment1.assignment3.repository.PrimaryEmployeeRepository;
import org.assignment1.assignment3.repository.SecondaryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private PrimaryEmployeeRepository primaryEmployeeRepo;

    @Autowired
    private SecondaryEmployeeRepository secondaryEmployeeRepo;

    public List<Employee> getAllEmployeesFromPrimary() {
        return primaryEmployeeRepo.getAllEmployees();
    }

    public Employee getEmployeeByIdFromPrimary(int id) {
        return primaryEmployeeRepo.getEmployeeById(id);
    }

    @Transactional("primaryTransactionManager")
    public int addEmployeeToPrimary(Employee employee) {
        return primaryEmployeeRepo.addEmployee(employee);
    }

    @Transactional("primaryTransactionManager")
    public int updateEmployeeFromPrimary(Employee employee) {
        return primaryEmployeeRepo.updateEmployee(employee);
    }

    public int deleteByIdFromPrimary(int id) {
        return primaryEmployeeRepo.deleteById(id);
    }

    // Methods for secondary data source
    public List<Employee> getAllEmployeesFromSecondary() {
        return secondaryEmployeeRepo.getAllEmployees();
    }

    public Employee getEmployeeByIdFromSecondary(int id) {
        return secondaryEmployeeRepo.getEmployeeById(id);
    }

    @Transactional("secondaryTransactionManager")
    public int addEmployeeToSecondary(Employee employee) {
        return secondaryEmployeeRepo.addEmployee(employee);
    }

    @Transactional("secondaryTransactionManager")
    public int updateEmployeeInSecondary(Employee employee) {
        return secondaryEmployeeRepo.updateEmployee(employee);
    }

    public int deleteByIdFromSecondary(int id) {
        return secondaryEmployeeRepo.deleteById(id);
    }
}

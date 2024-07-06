package org.assignment1.assignment3.service;

import org.assignment1.assignment3.model.employee;
import org.assignment1.assignment3.repository.primaryEmployeeRepository;
import org.assignment1.assignment3.repository.secondaryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class employeeService {

    @Autowired
    private primaryEmployeeRepository primaryEmployeeRepo;

    @Autowired
    private secondaryEmployeeRepository secondaryEmployeeRepo;

    public List<employee> getAllEmployeesFromPrimary() {
        return primaryEmployeeRepo.getAllEmployees();
    }

    public employee getEmployeeByIdFromPrimary(int id) {
        return primaryEmployeeRepo.getEmployeeById(id);
    }

    @Transactional("primaryTransactionManager")
    public int addEmployeeToPrimary(employee employee) {
        return primaryEmployeeRepo.addEmployee(employee);
    }

    @Transactional("primaryTransactionManager")
    public int updateEmployeeFromPrimary(employee employee) {
        return primaryEmployeeRepo.updateEmployee(employee);
    }

    public int deleteByIdFromPrimary(int id) {
        return primaryEmployeeRepo.deleteById(id);
    }

    // Methods for secondary data source
    public List<employee> getAllEmployeesFromSecondary() {
        return secondaryEmployeeRepo.getAllEmployees();
    }

    public employee getEmployeeByIdFromSecondary(int id) {
        return secondaryEmployeeRepo.getEmployeeById(id);
    }

    @Transactional("secondaryTransactionManager")
    public int addEmployeeToSecondary(employee employee) {
        return secondaryEmployeeRepo.addEmployee(employee);
    }

    @Transactional("secondaryTransactionManager")
    public int updateEmployeeInSecondary(employee employee) {
        return secondaryEmployeeRepo.updateEmployee(employee);
    }

    public int deleteByIdFromSecondary(int id) {
        return secondaryEmployeeRepo.deleteById(id);
    }
}

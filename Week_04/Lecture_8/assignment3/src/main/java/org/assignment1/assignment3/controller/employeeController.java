package org.assignment1.assignment3.controller;

import lombok.AllArgsConstructor;

import org.assignment1.assignment3.model.Employee;
import org.assignment1.assignment3.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Endpoints for primary data source
    @GetMapping("/primary")
    public List<Employee> getAllEmployeeFromPrimary() {
        return employeeService.getAllEmployeesFromPrimary();
    }

    @GetMapping("/primary/{id}")
    public Employee getEmployeeByIdFromPrimary(@PathVariable int id) {
        return employeeService.getEmployeeByIdFromPrimary(id);
    }

    @PostMapping("/primary/add")
    public int addEmployeeToPrimary(@RequestBody Employee employee) {
        return employeeService.addEmployeeToPrimary(employee);
    }

    @PutMapping("/primary/{id}")
    public int updateEmployeeFromPrimary(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.updateEmployeeFromPrimary(employee);
    }

    @DeleteMapping("/primary/{id}")
    public int deleteEmployeeFromPrimary(@PathVariable int id) {
        return employeeService.deleteByIdFromPrimary(id);
    }

    // Endpoints for secondary data source
    @GetMapping("/secondary")
    public List<Employee> getAllEmployeeFromSecondary() {
        return employeeService.getAllEmployeesFromSecondary();
    }

    @GetMapping("/secondary/{id}")
    public Employee getEmployeeByIdFromSecondary(@PathVariable int id) {
        return employeeService.getEmployeeByIdFromSecondary(id);
    }

    @PostMapping("/secondary/add")
    public int addEmployeeToSecondary(@RequestBody Employee employee) {
        return employeeService.addEmployeeToSecondary(employee);
    }

    @PutMapping("/secondary/{id}")
    public int updateEmployeeInSecondary(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.updateEmployeeInSecondary(employee);
    }

    @DeleteMapping("/secondary/{id}")
    public int deleteEmployeeFromSecondary(@PathVariable int id) {
        return employeeService.deleteByIdFromSecondary(id);
    }
}

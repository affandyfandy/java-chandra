package org.assignment1.assignment2.controller;

import lombok.AllArgsConstructor;
import org.assignment1.assignment2.model.employee;
import org.assignment1.assignment2.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class employeeController {

    @Autowired
    private employeeService employeeService;

    @GetMapping
    public List<employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/add")
    public int addEmployee(@RequestBody employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public int updateEmployee(@PathVariable int id, @RequestBody employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public int deleteEmployee(@PathVariable int id) {
        return employeeService.deleteById(id);
    }
}

package org.assignment1.assignment1.controller;

import org.assignment1.assignment1.dto.EmployeeDTO;
import org.assignment1.assignment1.dto.SearchEmployeeDynamicDTO;
import org.assignment1.assignment1.entity.Employee;
import org.assignment1.assignment1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<EmployeeDTO> employees = employeeService.getAllEmployees(page, size);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/details/{empNo}")
    public ResponseEntity<EmployeeDTO> getEmployeeWithDetails(@PathVariable int empNo) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(empNo);
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping("/{empNo}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int empNo, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(empNo, employeeDTO));
    }

    @DeleteMapping("/{empNo}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empNo) {
        employeeService.deleteEmployee(empNo);
        return ResponseEntity.ok("Employee successfully deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> searchEmployees(
            @ModelAttribute SearchEmployeeDynamicDTO criteria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Employee> employees = employeeService.searchEmployees(criteria, page, size);
        return ResponseEntity.ok(employees);
    }
}
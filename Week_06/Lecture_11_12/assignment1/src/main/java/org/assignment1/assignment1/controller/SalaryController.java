package org.assignment1.assignment1.controller;

import java.util.List;

import org.assignment1.assignment1.dto.SalaryDTO;
import org.assignment1.assignment1.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public ResponseEntity<List<SalaryDTO>> getAllSalaries() {
        return ResponseEntity.ok(salaryService.getAllSalaries());
    }

    @PostMapping
    public ResponseEntity<SalaryDTO> saveSalary(@RequestBody SalaryDTO salaryDTO) {
        return ResponseEntity.ok(salaryService.saveSalary(salaryDTO));
    }

    @PutMapping
    public ResponseEntity<SalaryDTO> updateSalary(@RequestBody SalaryDTO salaryDTO) {
        return ResponseEntity.ok(salaryService.updateSalary(salaryDTO));
    }
}

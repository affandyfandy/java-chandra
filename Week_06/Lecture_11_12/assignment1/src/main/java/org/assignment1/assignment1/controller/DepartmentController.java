package org.assignment1.assignment1.controller;

import org.assignment1.assignment1.dto.DepartmentDTO;
import org.assignment1.assignment1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{deptNo}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable String deptNo) {
        return ResponseEntity.ok(departmentService.getDepartmentById(deptNo));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.addDepartment(departmentDTO));
    }

    @PutMapping("/{deptNo}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable String deptNo,
            @RequestBody DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.updateDepartment(deptNo, departmentDTO));
    }

    @DeleteMapping("/{deptNo}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String deptNo) {
        departmentService.deleteDepartment(deptNo);
        return ResponseEntity.ok("Department successfully deleted");
    }

}

package org.assignment1.assignment1.controller;

import org.assignment1.assignment1.dto.DeptManagerDTO;
import org.assignment1.assignment1.service.DeptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/deptmanagers")
public class DeptManagerController {

    @Autowired
    private DeptManagerService deptManagerService;

    @GetMapping
    public ResponseEntity<List<DeptManagerDTO>> getAllDeptManagers() {
        return ResponseEntity.ok(deptManagerService.getAllDeptManagers());
    }

    @PostMapping
    public ResponseEntity<DeptManagerDTO> addDeptManager(@RequestBody DeptManagerDTO deptManagerDTO) {
        return ResponseEntity.ok(deptManagerService.addDeptManager(deptManagerDTO));
    }
}

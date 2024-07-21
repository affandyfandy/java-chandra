package org.assignment1.assignment1.controller;

import org.assignment1.assignment1.dto.DeptEmploysDTO;
import org.assignment1.assignment1.service.DeptEmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/deptemploys")
public class DeptEmployController {
    @Autowired
    private DeptEmployService deptEmployService;

    @GetMapping
    public ResponseEntity<List<DeptEmploysDTO>> getAllDeptEmploys() {
        return ResponseEntity.ok(deptEmployService.getAllDeptEmploys());
    }

    @PostMapping
    public ResponseEntity<DeptEmploysDTO> addDeptEmploy(@RequestBody DeptEmploysDTO deptEmployDTO) {
        return ResponseEntity.ok(deptEmployService.addDeptEmploy(deptEmployDTO));
    }

}

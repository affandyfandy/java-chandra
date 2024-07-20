package org.assignment1.assignment1.service;

import org.assignment1.assignment1.dto.DepartmentDTO;
import org.assignment1.assignment1.entity.Department;
import org.assignment1.assignment1.mapper.DepartmentMapper;
import org.assignment1.assignment1.repository.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DepartmentDTO getDepartmentById(String deptNo) {
        Department department = departmentRepository.findById(deptNo)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return departmentMapper.toDTO(department);
    }

    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.toEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.toDTO(savedDepartment);
    }

    public DepartmentDTO updateDepartment(String deptNo, DepartmentDTO departmentDTO) {
        Department existingDepartment = departmentRepository.findById(deptNo)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Department department = departmentMapper.toEntity(departmentDTO);
        department.setDeptNo(existingDepartment.getDeptNo());
        Department updatedDepartment = departmentRepository.save(department);
        return departmentMapper.toDTO(updatedDepartment);
    }

    public void deleteDepartment(String deptNo) {
        Department existingDepartment = departmentRepository.findById(deptNo)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(existingDepartment);
    }
}

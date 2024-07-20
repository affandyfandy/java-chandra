package org.assignment1.assignment1.service;

import java.util.List;
import java.util.stream.Collectors;
import org.assignment1.assignment1.dto.DeptManagerDTO;
import org.assignment1.assignment1.entity.DeptManager;
import org.assignment1.assignment1.mapper.DeptManagerMapper;
import org.assignment1.assignment1.repository.DepartmentRepository;
import org.assignment1.assignment1.repository.DeptManagerRepository;
import org.assignment1.assignment1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptManagerService {

    private final DeptManagerRepository deptManagerRepository;
    private final DeptManagerMapper deptManagerMapper;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DeptManagerService(DeptManagerRepository deptManagerRepository, DeptManagerMapper deptManagerMapper,
            EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {

        this.deptManagerRepository = deptManagerRepository;
        this.deptManagerMapper = deptManagerMapper;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<DeptManagerDTO> getAllDeptManagers() {
        return deptManagerRepository.findAll().stream()
                .map(deptManagerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DeptManagerDTO addDeptManager(DeptManagerDTO deptManagerDTO) {
        DeptManager deptManager = deptManagerMapper.toEntity(deptManagerDTO);
        deptManager.setEmployees(employeeRepository.findById(deptManagerDTO.getEmpNo())
                .orElseThrow(() -> new RuntimeException("Employee not found")));
        deptManager.setDepartments(departmentRepository.findById(deptManagerDTO.getDeptNo())
                .orElseThrow(() -> new RuntimeException("Department not found")));
        DeptManager savedDeptManager = deptManagerRepository.save(deptManager);
        return deptManagerMapper.toDTO(savedDeptManager);
    }
}

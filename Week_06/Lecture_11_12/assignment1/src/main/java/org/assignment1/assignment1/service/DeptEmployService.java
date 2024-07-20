package org.assignment1.assignment1.service;

import java.util.List;
import org.assignment1.assignment1.dto.DeptEmploysDTO;
import org.assignment1.assignment1.entity.DeptEmploy;
import org.assignment1.assignment1.mapper.DeptEmploysMapper;
import org.assignment1.assignment1.repository.DepartmentRepository;
import org.assignment1.assignment1.repository.DeptEmployRepository;
import org.assignment1.assignment1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class DeptEmployService {

    private final DeptEmployRepository deptEmployRepository;
    private final DeptEmploysMapper deptEmployMapper;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DeptEmployService(DeptEmployRepository deptEmployRepository, DeptEmploysMapper deptEmployMapper,
            EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {

        this.deptEmployRepository = deptEmployRepository;
        this.deptEmployMapper = deptEmployMapper;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<DeptEmploysDTO> getAllDeptEmploys() {
        return deptEmployRepository.findAll().stream()
                .map(deptEmployMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DeptEmploysDTO addDeptEmploy(DeptEmploysDTO deptEmployDTO) {
        DeptEmploy deptEmploy = deptEmployMapper.toEntity(deptEmployDTO);
        deptEmploy.setEmployees(employeeRepository.findById(deptEmployDTO.getEmpNo())
                .orElseThrow(() -> new RuntimeException("Employee not found")));
        deptEmploy.setDepartments(departmentRepository.findById(deptEmployDTO.getDeptNo())
                .orElseThrow(() -> new RuntimeException("Department not found")));
        DeptEmploy savedDeptEmploy = deptEmployRepository.save(deptEmploy);
        return deptEmployMapper.toDTO(savedDeptEmploy);
    }
}

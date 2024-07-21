package org.assignment1.assignment1.service;

import org.assignment1.assignment1.entity.DeptEmploy;
import org.assignment1.assignment1.entity.DeptManager;
import org.assignment1.assignment1.entity.Employee;
import org.assignment1.assignment1.entity.Salary;
import org.assignment1.assignment1.entity.Title;
import org.assignment1.assignment1.dto.EmployeeDTO;
import org.assignment1.assignment1.dto.SearchEmployeeDynamicDTO;
import org.assignment1.assignment1.mapper.DeptEmploysMapper;
import org.assignment1.assignment1.mapper.DeptManagerMapper;
import org.assignment1.assignment1.mapper.EmployeeMapper;
import org.assignment1.assignment1.mapper.SalaryMapper;
import org.assignment1.assignment1.mapper.TitleMapper;
import org.assignment1.assignment1.repository.DeptEmployRepository;
import org.assignment1.assignment1.repository.DeptManagerRepository;
import org.assignment1.assignment1.repository.EmployeeRepository;
import org.assignment1.assignment1.repository.SalaryRepository;
import org.assignment1.assignment1.repository.TitleRepository;
import org.assignment1.assignment1.specifications.EmployeeSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

        private final EmployeeRepository employeeRepository;
        private final EmployeeMapper employeeMapper;
        private final SalaryRepository salaryRepository;
        private final SalaryMapper salaryMapper;
        private final TitleRepository titleRepository;
        private final TitleMapper titleMapper;
        private final DeptEmployRepository deptEmployRepository;
        private final DeptEmploysMapper deptEmploysMapper;
        private final DeptManagerRepository deptManagerRepository;
        private final DeptManagerMapper deptManagerMapper;

        @Autowired
        public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
                        SalaryRepository salaryRepository, SalaryMapper salaryMapper, TitleRepository titleRepository,
                        TitleMapper titleMapper, DeptEmployRepository deptEmployRepository,
                        DeptEmploysMapper deptEmploysMapper,
                        DeptManagerRepository deptManagerRepository, DeptManagerMapper deptManagerMapper) {

                this.employeeRepository = employeeRepository;
                this.employeeMapper = employeeMapper;
                this.salaryRepository = salaryRepository;
                this.salaryMapper = salaryMapper;
                this.titleRepository = titleRepository;
                this.titleMapper = titleMapper;
                this.deptEmployRepository = deptEmployRepository;
                this.deptEmploysMapper = deptEmploysMapper;
                this.deptManagerRepository = deptManagerRepository;
                this.deptManagerMapper = deptManagerMapper;
        }

        public Page<EmployeeDTO> getAllEmployees(int page, int size) {
                Pageable pageable = PageRequest.of(page, size);
                Page<Employee> employeePage = employeeRepository.findAll(pageable);
                List<EmployeeDTO> employeeDTOs = employeePage.getContent().stream()
                                .map(employee -> {
                                        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);

                                        List<Salary> salaries = salaryRepository.findByIdEmpNo(employee.getEmpNo());
                                        employeeDTO.setSalaries(salaries.stream().map(salaryMapper::toDTO)
                                                        .collect(Collectors.toList()));

                                        List<Title> titles = titleRepository.findByIdEmpNo(employee.getEmpNo());
                                        employeeDTO.setTitles(titles.stream().map(titleMapper::toDTO)
                                                        .collect(Collectors.toList()));

                                        List<DeptEmploy> deptEmploys = deptEmployRepository
                                                        .findByIdEmpNo(employee.getEmpNo());
                                        employeeDTO.setDeptEmploys(deptEmploys.stream().map(deptEmploysMapper::toDTO)
                                                        .collect(Collectors.toList()));

                                        List<DeptManager> deptManagers = deptManagerRepository
                                                        .findByIdEmpNo(employee.getEmpNo());
                                        employeeDTO.setDeptManagers(deptManagers.stream().map(deptManagerMapper::toDTO)
                                                        .collect(Collectors.toList()));

                                        return employeeDTO;
                                })
                                .collect(Collectors.toList());

                return new PageImpl<>(employeeDTOs, pageable, employeePage.getTotalElements());
        }

        @Transactional(readOnly = true)
        public EmployeeDTO getEmployeeById(int empNo) {
                Employee employee = employeeRepository.findById(empNo)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Employee not found with id " + empNo));

                EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);

                // Fetch related entities using the repository methods
                List<Salary> salaries = salaryRepository.findById_EmpNo(empNo);
                employeeDTO.setSalaries(salaries.stream().map(salaryMapper::toDTO).collect(Collectors.toList()));

                List<Title> titles = titleRepository.findById_EmpNo(empNo);
                employeeDTO.setTitles(titles.stream().map(titleMapper::toDTO).collect(Collectors.toList()));

                List<DeptEmploy> deptEmploys = deptEmployRepository.findById_EmpNo(empNo);
                employeeDTO.setDeptEmploys(
                                deptEmploys.stream().map(deptEmploysMapper::toDTO).collect(Collectors.toList()));

                List<DeptManager> deptManagers = deptManagerRepository.findById_EmpNo(empNo);
                employeeDTO.setDeptManagers(
                                deptManagers.stream().map(deptManagerMapper::toDTO).collect(Collectors.toList()));

                return employeeDTO;
        }

        public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
                Employee employee = employeeMapper.toEntity(employeeDTO);
                return employeeMapper.toDTO(employeeRepository.save(employee));
        }

        public EmployeeDTO updateEmployee(int empNo, EmployeeDTO employeeDTO) {
                Employee existingEmployee = employeeRepository.findById(empNo)
                                .orElseThrow(() -> new RuntimeException("Employee not found"));
                Employee updatedEmployee = employeeMapper.toEntity(employeeDTO);
                updatedEmployee.setEmpNo(existingEmployee.getEmpNo());
                return employeeMapper.toDTO(employeeRepository.save(updatedEmployee));
        }

        public void deleteEmployee(int empNo) {
                employeeRepository.deleteById(empNo);
        }

        @Transactional(readOnly = true)
        public Page<Employee> searchEmployees(SearchEmployeeDynamicDTO criteria, int page, int size) {
                Pageable pageable = PageRequest.of(page, size);
                Specification<Employee> spec = EmployeeSpecifications.withCriteria(criteria);
                return employeeRepository.findAll(spec, pageable);
        }
}

package org.assignment1.assignment1.service;

import org.assignment1.assignment1.dto.TitleDTO;
import org.assignment1.assignment1.entity.Employee;
import org.assignment1.assignment1.entity.Title;
import org.assignment1.assignment1.repository.EmployeeRepository;
import org.assignment1.assignment1.repository.TitleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.assignment1.assignment1.mapper.TitleMapper;

@Service
public class TitleService {

    private TitleRepository titleRepository;
    private TitleMapper titleMapper;
    private EmployeeRepository employeeRepository;

    @Autowired
    public TitleService(TitleRepository titleRepository, TitleMapper titleMapper,
            EmployeeRepository employeeRepository) {
        this.titleRepository = titleRepository;
        this.titleMapper = titleMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<TitleDTO> getAllTitles() {
        List<Title> titles = titleRepository.findAll();
        return titles.stream()
                .map(titleMapper::toDTO)
                .toList();
    }

    public TitleDTO saveTitle(TitleDTO titleDTO) {
        // Validate that the employee exists
        Employee employee = employeeRepository.findById(titleDTO.getEmpNo())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Convert DTO to entity
        Title title = titleMapper.toEntity(titleDTO);
        title.setEmployees(employee);

        // Save the entity
        Title savedTitle = titleRepository.save(title);
        return titleMapper.toDTO(savedTitle);
    }

    public TitleDTO updateTitle(TitleDTO titleDTO) {
        // Validate that the employee exists
        Employee employee = employeeRepository.findById(titleDTO.getEmpNo())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Convert DTO to entity
        Title title = titleMapper.toEntity(titleDTO);
        title.setEmployees(employee);

        // Save the entity
        Title updatedTitle = titleRepository.save(title);
        return titleMapper.toDTO(updatedTitle);
    }
}
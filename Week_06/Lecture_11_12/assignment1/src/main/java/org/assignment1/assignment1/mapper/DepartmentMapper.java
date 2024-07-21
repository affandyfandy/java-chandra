package org.assignment1.assignment1.mapper;

import org.assignment1.assignment1.dto.DepartmentDTO;
import org.assignment1.assignment1.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO toDTO(Department department);

    Department toEntity(DepartmentDTO departmentDTO);
}
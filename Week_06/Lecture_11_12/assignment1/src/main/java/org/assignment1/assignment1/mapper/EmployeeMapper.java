package org.assignment1.assignment1.mapper;

import org.assignment1.assignment1.entity.Employee;
import org.assignment1.assignment1.dto.EmployeeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDTO(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

}

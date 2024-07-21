package org.assignment1.assignment1.mapper;

import org.assignment1.assignment1.dto.EmployeeDTO;
import org.assignment1.assignment1.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore mapping id during DTO to entity conversion
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    void updateEmployeeFromDTO(EmployeeDTO employeeDTO, @MappingTarget Employee employee);
}

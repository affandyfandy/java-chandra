package org.assignment1.assignment1.mapper;

import org.assignment1.assignment1.entity.Salary;
import org.assignment1.assignment1.dto.SalaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SalaryMapper {

        @Mappings({
                        @Mapping(source = "empNo", target = "id.empNo"),
                        @Mapping(source = "fromDate", target = "id.fromDate")
        })
        Salary toEntity(SalaryDTO salaryDTO);

        @Mappings({
                        @Mapping(source = "id.empNo", target = "empNo"),
                        @Mapping(source = "id.fromDate", target = "fromDate")
        })
        SalaryDTO toDTO(Salary salary);
}
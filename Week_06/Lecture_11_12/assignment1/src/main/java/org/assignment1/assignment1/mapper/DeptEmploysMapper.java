package org.assignment1.assignment1.mapper;

import org.assignment1.assignment1.dto.DeptEmploysDTO;
import org.assignment1.assignment1.entity.DeptEmploy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DeptEmploysMapper {

        @Mappings({
                        @Mapping(target = "id.deptNo", source = "deptNo"),
                        @Mapping(target = "id.empNo", source = "empNo"),
        })
        DeptEmploy toEntity(DeptEmploysDTO dto);

        @Mappings({
                        @Mapping(target = "deptNo", source = "id.deptNo"),
                        @Mapping(target = "empNo", source = "id.empNo"),
        })
        DeptEmploysDTO toDTO(DeptEmploy entity);
}
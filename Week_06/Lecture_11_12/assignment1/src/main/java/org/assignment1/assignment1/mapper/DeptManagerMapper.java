package org.assignment1.assignment1.mapper;

import org.assignment1.assignment1.dto.DeptManagerDTO;
import org.assignment1.assignment1.entity.DeptManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DeptManagerMapper {

    @Mappings({
            @Mapping(target = "id.deptNo", source = "deptNo"),
            @Mapping(target = "id.empNo", source = "empNo"),
            @Mapping(target = "fromDate", source = "fromDate"),
            @Mapping(target = "toDate", source = "toDate")
    })
    DeptManager toEntity(DeptManagerDTO dto);

    @Mappings({
            @Mapping(target = "deptNo", source = "id.deptNo"),
            @Mapping(target = "empNo", source = "id.empNo"),
            @Mapping(target = "fromDate", source = "fromDate"),
            @Mapping(target = "toDate", source = "toDate")
    })
    DeptManagerDTO toDTO(DeptManager entity);
}
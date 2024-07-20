package org.assignment1.assignment1.mapper;

import org.assignment1.assignment1.dto.TitleDTO;
import org.assignment1.assignment1.entity.Title;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TitleMapper {

    @Mappings({
            @Mapping(source = "empNo", target = "id.empNo"),
            @Mapping(source = "title", target = "id.title"),
            @Mapping(source = "fromDate", target = "id.fromDate")
    })
    Title toEntity(TitleDTO dto);

    TitleDTO toDTO(Title entity);
}
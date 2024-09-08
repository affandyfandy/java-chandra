package com.assignment2.resource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.assignment2.resource.dto.ProductDTO;
import com.assignment2.resource.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);
}

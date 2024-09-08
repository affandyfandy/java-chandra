package com.assignment1.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.assignment1.security.dto.ProductDTO;
import com.assignment1.security.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);
}

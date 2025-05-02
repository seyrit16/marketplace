package com.example.product_service.service.mapper;

import com.example.product_service.dto.response.CategoryResponse;
import com.example.product_service.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.elasticsearch.annotations.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);
}

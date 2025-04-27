package com.example.product_service.service.mapper;

import com.example.product_service.dto.request.ProductAttrValCreateRequest;
import com.example.product_service.model.ProductAttributeValue;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductAttributeValueMapper {

    ProductAttributeValue fromProductAttrValCreateRequest(ProductAttrValCreateRequest request);
}

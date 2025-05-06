package com.example.product_service.service.mapper;

import com.example.product_service.dto.request.ProductCreateRequest;
import com.example.product_service.dto.request.ProductUpdateRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.model.ProductAttributeValue;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product fromProductCreateRequest(ProductCreateRequest request,@MappingTarget Product product);

    ProductResponse toProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(ProductUpdateRequest src, @MappingTarget Product dest);
}

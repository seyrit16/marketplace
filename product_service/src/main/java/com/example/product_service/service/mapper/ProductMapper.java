package com.example.product_service.service.mapper;

import com.example.product_service.dto.request.ProductCreateRequest;
import com.example.product_service.model.Product;
import com.example.product_service.model.ProductAttributeValue;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(target = "files", source = "")
    @Mapping(source = "attributes",target = "attributes")
    Product fromProductCreateRequest(ProductCreateRequest request,@MappingTarget Product product);

    @AfterMapping
    default void setReverseRelations(@MappingTarget Product product) {
        if(product.getAttributes() != null){
            for(ProductAttributeValue attribute: product.getAttributes()){
                attribute.setProduct(product);
            }
        }
    }
}

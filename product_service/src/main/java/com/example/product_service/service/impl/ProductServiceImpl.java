package com.example.product_service.service.impl;

import com.example.product_service.client.SellerClient;
import com.example.product_service.dto.request.ProductAttrValCreateRequest;
import com.example.product_service.dto.request.ProductCreateRequest;
import com.example.product_service.dto.request.ProductUpdateRequest;
import com.example.product_service.dto.rest.SellerForCreateProductResponse;
import com.example.product_service.exception.UserNotFoundException;
import com.example.product_service.model.Product;
import com.example.product_service.model.ProductAttributeValue;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductSearchService;
import com.example.product_service.service.ProductService;
import com.example.product_service.service.mapper.ProductAttributeValueMapper;
import com.example.product_service.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductSearchService productSearchService;
    private final ProductRepository productRepository;
    private final SellerClient sellerClient;
    private final ProductMapper productMapper;
    private final ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    public ProductServiceImpl(ProductSearchService productSearchService, ProductRepository productRepository, SellerClient sellerClient, ProductMapper productMapper, ProductAttributeValueMapper productAttributeValueMapper) {
        this.productSearchService = productSearchService;
        this.productRepository = productRepository;
        this.sellerClient = sellerClient;
        this.productMapper = productMapper;
        this.productAttributeValueMapper = productAttributeValueMapper;
    }


    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product create(ProductCreateRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SellerForCreateProductResponse response = sellerClient.getSellerData().getBody();
        Product product = new Product();
        if(authentication.getCredentials() instanceof UUID sellerId){
            product.setSellerId(sellerId);
        }else{
            throw new UserNotFoundException("Ошибка при создании продукта: пользователь не найден.");
        }

        productMapper.fromProductCreateRequest(request,product);
        List<ProductAttributeValue> attributeList = new ArrayList<>();
        for(ProductAttrValCreateRequest attributeDTO: request.getAttributes()){
            ProductAttributeValue attribute = productAttributeValueMapper.fromProductAttrValCreateRequest(attributeDTO);
            attribute.setProduct(product);
            attributeList.add(attribute);
        }
        product.setAttributes(attributeList);
        /*TODO 27.04 добавить обработку файлов передаваемых при создании продукта
         1. сохранение файлов в хранилище в отдельную директорию для продукта с таким-то id
         2. сохранение url директории в базу
         */

        return save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Product update(ProductUpdateRequest request) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Page<Product> getAllProductsBySellerId(UUID id, Pageable pageable) {
        return null;
    }
}

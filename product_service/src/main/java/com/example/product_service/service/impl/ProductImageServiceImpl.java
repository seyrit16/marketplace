package com.example.product_service.service.impl;

import com.example.product_service.dto.request.ProductImageCreateRequest;
import com.example.product_service.dto.request.ProductImageUpdateRequest;
import com.example.product_service.model.Product;
import com.example.product_service.model.ProductImage;
import com.example.product_service.repository.ProductImageRepository;
import com.example.product_service.service.LocalFileStorageService;
import com.example.product_service.service.ProductImageService;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductService productService;
    private final LocalFileStorageService localFileStorageService;

    @Value("${file.paths.products.relative-location}")
    private String IMG_DIR_PATH;

    @Autowired
    public ProductImageServiceImpl(ProductImageRepository productImageRepository, ProductService productService, LocalFileStorageService localFileStorageService) {
        this.productImageRepository = productImageRepository;
        this.productService = productService;
        this.localFileStorageService = localFileStorageService;
    }

    @Override
    @Transactional
    public ProductImage save(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    @Override
    @Transactional
    public ProductImage create(ProductImageCreateRequest data, MultipartFile file){

        Product product = productService.getProductById(data.getProductId());
        String filedir = IMG_DIR_PATH + "\\" + data.getProductId();
        String filename = data.getProductId() + "-" + file.getOriginalFilename();

        ProductImage image = new ProductImage();
        image.setProduct(product);
        image.setFileName(filename);
        image.setUrl(filedir + "\\" + filename);
        image.setSortOrder(data.getSortOrder());
        localFileStorageService.save(image.getUrl(), file);

        return save(image);
    }

    @Override
    @Transactional
    public void update(List<ProductImageUpdateRequest> productImages) {

        List<ProductImage> images = new ArrayList<>();
        for (ProductImageUpdateRequest dto: productImages){
            switch (dto.getStatus()){
                case UPDATE -> {
                    ProductImage img = productImageRepository.findById(dto.getId()).get();
                    img.setSortOrder(dto.getSortOrder());
                    images.add(img);
                }
                case DELETE -> deleteById(dto.getId());
            }
        }
        productImageRepository.saveAll(images);
    }

    @Override
    @Transactional
    public void delete(ProductImage productImage) {
        productImageRepository.delete(productImage);
        localFileStorageService.delete(productImage.getUrl());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        ProductImage productImage = productImageRepository.findById(id).get();
        productImageRepository.deleteById(id);
        localFileStorageService.delete(productImage.getUrl());
    }
}

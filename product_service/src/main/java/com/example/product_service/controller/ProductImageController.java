package com.example.product_service.controller;

import com.example.product_service.dto.request.ProductImageCreateRequest;
import com.example.product_service.dto.request.ProductImageUpdateRequest;
import com.example.product_service.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product_image")
public class ProductImageController {

    private final ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProductImage(@RequestPart("data") ProductImageCreateRequest data,
                                                   @RequestPart("file") MultipartFile file
    ) {
        productImageService.create(data,file);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update_product_imgs")
    public ResponseEntity<Void> updateProductImages(@RequestBody List<ProductImageUpdateRequest> data){

        productImageService.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProductImage(@RequestParam(name = "id") Long id) {
        productImageService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}

package com.example.product_service.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface LocalFileStorageService {
    String save(String filename,MultipartFile file);
    Resource load(String filename);
    void delete(String filename);
}

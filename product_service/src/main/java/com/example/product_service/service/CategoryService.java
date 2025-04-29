package com.example.product_service.service;

import com.example.product_service.dto.request.CategoryCreateRequest;
import com.example.product_service.model.Category;

public interface CategoryService {
    Category save(Category category);
    Category createCategory(CategoryCreateRequest data);
    Category getCategoryById(Long id);
    Category getRootCategory();
    void delete(Long id);
}

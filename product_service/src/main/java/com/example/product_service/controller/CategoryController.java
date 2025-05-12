package com.example.product_service.controller;

import com.example.product_service.dto.request.CategoryCreateRequest;
import com.example.product_service.dto.response.CategoryResponse;
import com.example.product_service.model.Category;
import com.example.product_service.service.CategoryService;
import com.example.product_service.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@Validated
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping()
    public ResponseEntity<CategoryResponse> getCategoryById(@Positive(message = "Идентификатор категории должен быть положительным числом") @RequestParam(name = "id") Long id) {
        Category category = categoryService.getCategoryById(id);
        CategoryResponse response = categoryMapper.toCategoryResponse(category);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/sub")
    public ResponseEntity<List<CategoryResponse>> getSubcategoriesById(@Positive(message = "Идентификатор категории должен быть положительным числом") @RequestParam(name = "id") Long id) {
        Category category = categoryService.getCategoryById(id);
        List<CategoryResponse> response = category.getSubcategories()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryCreateRequest data) {
        categoryService.createCategory(data);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteCategory(@Positive(message = "Идентификатор категории должен быть положительным числом") @RequestParam(name = "id") Long id) {
        categoryService.delete(id);

        return ResponseEntity.ok().build();
    }
}
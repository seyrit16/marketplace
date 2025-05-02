package com.example.product_service.service.impl;

import com.example.product_service.dto.request.CategoryCreateRequest;
import com.example.product_service.exception.CategoryNotFoundException;
import com.example.product_service.model.Category;
import com.example.product_service.model.Product;
import com.example.product_service.repository.CategoryRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category createCategory(CategoryCreateRequest data) {
        Category category = new Category();
        category.setName(data.getName());
        if(data.getRootCategoryId() == null){
            category.setParentCategory(getRootCategory());
        }else{
            category.setParentCategory(getCategoryById(data.getRootCategoryId()));
        }

        return save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Категория не найдена.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Category getRootCategory() {
        return categoryRepository.findCategoryByParentCategoryIsNull().get();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = getCategoryById(id);
        Category parent = category.getParentCategory();
        if (parent == null) {
            throw new IllegalStateException("Нельзя удалить корневую категорию");
        }

        //переназначение продуктов
        for (Product product : category.getProducts()) {
            product.setCategory(parent);
        }
        productRepository.saveAll(category.getProducts());

        //переназначение дочерних категорий
        for (Category subcategory : category.getSubcategories()) {
            subcategory.setParentCategory(parent);
        }
        categoryRepository.saveAll(category.getSubcategories());

        categoryRepository.delete(category);
    }
}

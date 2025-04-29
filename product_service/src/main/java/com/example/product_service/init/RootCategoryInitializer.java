package com.example.product_service.init;

import com.example.product_service.model.Category;
import com.example.product_service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RootCategoryInitializer implements ApplicationRunner {
    private final CategoryRepository categoryRepository;

    @Autowired
    public RootCategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(categoryRepository.count() == 0){
            Category root = new Category();
            root.setName("Root");
            root.setParentCategory(null);
            categoryRepository.save(root);
        }
    }
}

package com.example.product_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category")
    private Category parentCategory;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Product> products;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.PERSIST)
    private List<Category> subcategories;

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addSubcategory(Category category) {
        subcategories.add(category);
    }
}

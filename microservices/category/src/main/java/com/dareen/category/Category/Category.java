package com.dareen.category.Category;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Category {
    

   private @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long category_id;
     
     @NotBlank(message = "Category name is required")
     @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String categoryName;

    @JsonManagedReference(value="category")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Products> products;

    public Category() {
        this.products = new HashSet<>();
    }

    public Category(String name) {
        this.categoryName = name;
        this.products = new HashSet<>();
    }

    public Long getId() {
        return category_id;
    }

    public void setId(Long id) {
        this.category_id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + category_id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}

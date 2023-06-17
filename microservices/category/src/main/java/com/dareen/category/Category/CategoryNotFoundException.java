package com.dareen.category.Category;



public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long categoryId) {
        super("Category not found with id: " + categoryId);
    }

    public CategoryNotFoundException(String categoryName) {
        super("Category not found with name: " + categoryName);
    }
}

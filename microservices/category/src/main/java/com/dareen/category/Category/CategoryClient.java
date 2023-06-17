package com.dareen.category.Category;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service")
public interface CategoryClient {
    @GetMapping("/categories/{id}")
    Category getCategory(@PathVariable("id") Long id);
}

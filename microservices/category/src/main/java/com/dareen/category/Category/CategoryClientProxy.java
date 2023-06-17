package com.dareen.category.Category;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service")
public interface CategoryClientProxy {

    @GetMapping("/category/{from}/{to}")
    public Category retrieveCategoryValue(@PathVariable("from") String from, @PathVariable("to") String to);
}

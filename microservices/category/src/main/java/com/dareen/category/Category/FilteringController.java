package com.dareen.category.Category;



import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/filtering")
public class FilteringController {
    private final CategoryRepository repository;
    FilteringController(CategoryRepository repository) {
        this.repository = repository;
    }
   

    @GetMapping("/category/{id}")
    public MappingJacksonValue retrieveSomeBean(@PathVariable Long id) {
        Category category = repository.findById(id) //
                .orElseThrow(() -> new CategoryNotFoundException(id));
        // invoking static method filterOutAllExcept()
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("category_id","name");
        // creating filter using FilterProvider class
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("CategoryFilter", filter);
        // constructor of MappingJacksonValue class that has bean as constructor
        // argument
        MappingJacksonValue mapping = new MappingJacksonValue(category);
        // configuring filters
        mapping.setFilters(filterProvider);
        return mapping;
    }
}

// @RestController
// @RequestMapping("/filtering")
// public class FilteringController {


//     private final CategoryRepository categoryRepository;

//     FilteringController(CategoryRepository categoryRepository) {
//         this.categoryRepository = categoryRepository;
//     }

//     @GetMapping("/Categores/{category_id}")
//     public MappingJacksonValue retrieveSomeBean(@PathVariable long category_id) {
//         Category category = categoryRepository.findById(category_id)
//         .orElseThrow(() -> new CategoryNotFoundException(category_id));
//          // invoking static method filterOutAllExcept()
//         SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("category_id", "categoryName");

//         // create the filter provider and add the filter to it
//         FilterProvider filterProvider = new SimpleFilterProvider().addFilter("CategoryFilter", filter);

//         // create the mapping object with the data and filters
//         MappingJacksonValue mapping = new MappingJacksonValue(category);
//         mapping.setFilters(filterProvider);

//         return mapping;
//     }
//     @Configuration
//     public static class JacksonConfig {

//         @Bean
//         public FilterProvider categoryFilter() {
//             SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("category_id");
//             return new SimpleFilterProvider().addFilter("CategoryFilter", filter);
//         }

//         @Bean
//         public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//             MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//             ObjectMapper objectMapper = new ObjectMapper();
//             objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter("CategoryFilter", SimpleBeanPropertyFilter.serializeAll()));
//             converter.setObjectMapper(objectMapper);
//             return converter;
//         }
//     }
// }

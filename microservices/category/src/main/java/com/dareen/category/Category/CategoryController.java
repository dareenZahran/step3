package com.dareen.category.Category;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
// import org.springframework.hateoas.IanaLinkRelations;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import com.project.demo.CategoryCategoryNotFoundException;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryRepository repository;
    private final CategoryModelAssembler assembler;

    CategoryController(CategoryRepository repository, CategoryModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    // @CrossOrigin
    @GetMapping("/Category")
    CollectionModel<EntityModel<Category>> all() {
        List<EntityModel<Category>> Category = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(Category, linkTo(methodOn(CategoryController.class).all()).withSelfRel());
    }
    

    @PostMapping("/Category")
    Category newCategory(@Valid@RequestBody Category newCategory) {
        return repository.save(newCategory);
    }
//     @PostMapping("/Category")
// public ResponseEntity<Object> newCategory(@Valid @RequestBody Category newCategory, BindingResult result) {
//     if (result.hasErrors()) {
//         // Return an error response with a custom message indicating the validation errors
//         List<String> errorMessages = new ArrayList<>();
//         for (FieldError error : result.getFieldErrors()) {
//             errorMessages.add(error.getDefaultMessage());
//         }
//         return ResponseEntity.badRequest().body(errorMessages);
//     } else if (newCategory.getCategoryName() == null || newCategory.getCategoryName().isEmpty()) {
//         // Return an error response if the input is empty
//         String errorMessage = "Category name cannot be empty";
//         return ResponseEntity.badRequest().body(errorMessage);
//     } 
//     else {
//         // Save the category to the repository
//         Category savedCategory = repository.save(newCategory);
//         // Return a response with the saved category and a link to view all categories
//         EntityModel<Category> categoryModel = assembler.toModel(savedCategory);
//         return ResponseEntity.created(categoryModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(categoryModel);
//     }
// }

    @GetMapping("/Category/{id}")
    EntityModel<Category> one(@PathVariable Long id) {
        Category Category = repository.findById(id) //
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return assembler.toModel(Category);
    }
    @PutMapping("/Category/{id}")
    Category replaceCategory(@Valid@RequestBody Category newCategory, @PathVariable Long id) {
        return repository.findById(id)
                .map(Category -> {
                    Category.setCategoryName(newCategory.getCategoryName());
                    return repository.save(Category);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return repository.save(newCategory);
                });
    }

    @DeleteMapping("/Category/{id}")
    void deleteCategory(@PathVariable Long id) {
        repository.deleteById(id);
    }
   
    
    
}

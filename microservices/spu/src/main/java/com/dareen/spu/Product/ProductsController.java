package com.dareen.spu.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import javax.validation.Valid;

// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dareen.spu.security.jwt.JwtUtils;


@CrossOrigin
@RestController
public class ProductsController {

    private final ProductsRepository repository;
    private final ProductsModelAssemble assembler;
    private final JwtUtils jwtUtils;
    @Autowired
private Category categoryClient;

    public ProductsController(ProductsRepository repository, ProductsModelAssemble assembler, JwtUtils jwtUtils ,Category categoryClient) {
        this.repository = repository;
        this.assembler = assembler;
        this.jwtUtils=jwtUtils;
        this.categoryClient = categoryClient;
    }

    
    @GetMapping("/your-endpoint")
    public ResponseEntity<String> yourEndpoint(@RequestHeader("Authorization") String authorizationHeader) {

        
        // Extract the token from the Authorization header
        String token = authorizationHeader.replace("Bearer ", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYWxzYWJlbCIsImlhdCI6MTY4NTg5ODUxMywiZXhwIjoxNjg1OTg0OTEzfQ.4F-vejuFutsZ9pklFkH3Lxu2i0aS6K6JfpNePFJzNIPsqEGnadSEghm10GkaUuhghkreSIX04SeJXO9GxiIAgA");

        // Validate the token
        if (jwtUtils.validateJwtToken(token)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        }

       
    }

    @GetMapping("/products")
    CollectionModel<EntityModel<Products>> all() {
   List<EntityModel<Products>> products = repository.findAll().stream() 
                .map(assembler::toModel) //
                .collect(Collectors.toList());
                return CollectionModel.of(products, linkTo(methodOn(ProductsController.class).all()).withSelfRel());
            }









    @PostMapping("/products")
    public ResponseEntity<Object> newProduct(@Valid @RequestBody Products newProduct, BindingResult result) {
        if (result.hasErrors()) {
            // Return an error response with a custom message indicating the validation errors
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        } else if (newProduct.getName() == null || newProduct.getName().isEmpty()) {
            // Return an error response if the input is empty
            String errorMessage = "Product name cannot be empty";
            return ResponseEntity.badRequest().body(errorMessage);
        } else if (newProduct.getDescription() == null || newProduct.getDescription().isEmpty()) {
            // Return an error response if the input is empty
            String errorMessage = "Product describttion cannot be empty";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        // else if (newProduct.getProductCode() == null || newProduct.getProductCode().isEmpty()) {
        //     // Return an error response if the input is empty
        //     String errorMessage = "Product code cannot be empty";
        //     return ResponseEntity.badRequest().body(errorMessage);
        // }
        else if (newProduct.getPrice() <=0 ) {
            // Return an error response if the input is empty
            String errorMessage = "make sure about Product price";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        
        else {
            // Save the category to the repository
            Products savedproducts = repository.save(newProduct);
            // Return a response with the saved category and a link to view all categories
            // EntityModel<Products> productsModel = assembler.toModel(savedproducts);
            EntityModel<Products> productsModel = assembler.toModel(savedproducts);
            return ResponseEntity.created(productsModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productsModel);
        }
    }
    // @CrossOrigin
    @GetMapping("/products/{id}")
    EntityModel<Products> one(@PathVariable Long id) {
        Products product = repository.findById(id) //
                .orElseThrow(() -> new ProductsNotFoundException(id));
        return assembler.toModel(product);
    }

    @PutMapping("/products/{id}")
    Products replaceProduct(@Valid@RequestBody Products newProduct, @PathVariable Long id) {
        return repository.findById(id) //
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    return repository.save(product);
                }) //
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

package com.dareen.category.Category;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CategoryModelAssembler  implements RepresentationModelAssembler<Category, EntityModel<Category>>{
    @Override
    public EntityModel<Category> toModel(Category Category) {
      return EntityModel.of(Category,
          linkTo(methodOn(CategoryController.class).one(Category.getId())).withSelfRel(),
          linkTo(methodOn(CategoryController.class).all()).withRel("Category"));
    }
}

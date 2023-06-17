package com.dareen.spu.Product;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductsModelAssemble implements RepresentationModelAssembler<Products, EntityModel<Products>> {

  @Override
  public EntityModel<Products> toModel(Products product) {
    return EntityModel.of(product,
        linkTo(methodOn(ProductsController.class).one(product.getId())).withSelfRel(),
        linkTo(methodOn(ProductsController.class).all()).withRel("products"));
  }
}

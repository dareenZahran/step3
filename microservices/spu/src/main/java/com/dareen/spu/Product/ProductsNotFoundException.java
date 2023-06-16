package com.project.demo.Product;

public class ProductsNotFoundException extends RuntimeException {
	
	public ProductsNotFoundException(Long id) {
	    super("Could not find product " + id);
	}

}

package com.dareen.spu.Product;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.project.demo.Category.Category;
// import com.project.demo.Order.Order;
// import com.project.demo.Supplier.Supplier;
 @Entity
// @Table(name = "products")
public class Products {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "product_id")
    private Long id;
    
   // @Column(name = "name")
   @NotBlank(message = "Product name is required")
    private String name;
    
    //@Column(name = "description")
    @NotBlank(message = "Product description is required")
    private String description;
    
   // @Column(name = "price")
   @NotNull(message = "Product  price is required")
    private double price;

    @NotNull(message = "no photo her")
    private String photo;
	
    
    // @ManyToMany(mappedBy = "products")
    // private List<Order> orders;
    // @NotBlank(message = "Product  Code is required")
	// private String productCode;


   @NotNull(message = "Category ID is required")
    private Long categoryId;
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    // @JsonBackReference
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "supplier_id")
    // // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    // private Supplier supplier;

    // // existing methods
    
    // public Category getCategory() {
    //     return category;
    // }

    // public void setCategory(Category category) {
    //     this.category = category;
    // }

    // public Supplier getSupplier() {
    //     return supplier;
    // }

    // public void setSupplier(Supplier supplier) {
    //     this.supplier = supplier;
    // }

    
    // public String getProductCode() {
    //     return productCode;
    // }

    // public void setProductCode(String productCode) {
    //     this.productCode = productCode;
    // }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // default constructor
    public Products() {
    }
    
    // constructor with arguments
    public Products(String name, String description, double price ,String photo ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }
 
    
    // getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    // toString method for debugging
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
    }

}

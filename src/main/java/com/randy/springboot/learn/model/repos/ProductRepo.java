package com.randy.springboot.learn.model.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.randy.springboot.learn.model.entities.Product;
import com.randy.springboot.learn.model.entities.Vendor;

import jakarta.websocket.server.PathParam;

public interface ProductRepo extends CrudRepository<Product, Long>{
    
    //buat costum QUERY fungsi diluar dari CrudRepository
    List<Product> findByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name ")
    public Product findProductbyName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :vendor MEMBER OF p.vendor")
    public List<Product> findProductByVendor(@PathParam("vendor") Vendor vendor);

    //Derived Query Methods di VendorRepo.java
}

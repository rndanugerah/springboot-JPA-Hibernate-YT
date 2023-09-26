package com.randy.springboot.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.randy.springboot.learn.dto.ResponseData;
import com.randy.springboot.learn.dto.SearchDto;
import com.randy.springboot.learn.model.entities.Product;
import com.randy.springboot.learn.model.entities.Vendor;
import com.randy.springboot.learn.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @PutMapping()
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

         if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
    }

    @PostMapping("/{id}")
    public void addVendor(@RequestBody Vendor vendor, @PathVariable("id") long productId){
        productService.addVendor(vendor, productId);
    }

    @PostMapping("/search/name")
    public Product getProductbyName(@RequestBody SearchDto searchDto){
        return productService.findbyName(searchDto.getSearchKey());
    }

    @PostMapping("/search/name/like")
    public List<Product> getProductbyNameLike(@RequestBody SearchDto searchDto){
        return productService.findbyNameLike(searchDto.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> getProductbyCategory(@PathVariable("categoryId") Long categoryId){
        return productService.findbyCategory(categoryId);
    }

    @GetMapping("/search/vendor/{vendorId}")
    public List<Product> getProductbyVendor(@PathVariable("vendorId") Long vendorId){
        return productService.findbyVendor(vendorId);
    }
}

package com.randy.springboot.learn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.randy.springboot.learn.model.entities.Product;
import com.randy.springboot.learn.model.entities.Vendor;
import com.randy.springboot.learn.model.repos.ProductRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private VendorService vendorService;

    //create and update -> tergantung apakah ada ID nya ada atau belum
    public Product save(Product product){
        return productRepo.save(product);
    }

    //find 1 ID
    public Product findOne(Long id){
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return productRepo.findById(id).get();
    }

    //ngambil semua ID
    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    //hapus salah satu ID
    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    public List<Product> findByNameContains(String name){
        return productRepo.findByNameContains(name);
    }

    //biderictional relation -> many to many
    public void addVendor(Vendor vendor, Long productId){
        Product product = findOne(productId);
        if(product==null){
            throw new RuntimeException("Product with ID: " +productId+ " not found");
        }
        product.getVendor().add(vendor);
        save(product);
    }

    public Product findbyName(String name){
        return productRepo.findProductbyName(name);
    }

    public List<Product> findbyNameLike(String name){
        return productRepo.findProductByNameLike("%"+name+"%");
    }

    public List<Product> findbyCategory(long categoryId){
        return productRepo.findProductByCategory(categoryId);
    }

    public List<Product> findbyVendor(Long vendorId){
        Vendor vendor = vendorService.findOne(vendorId);
        if(vendor == null){
            return new ArrayList<Product>();
        }
        return productRepo.findProductByVendor(vendor);
    }
  
}

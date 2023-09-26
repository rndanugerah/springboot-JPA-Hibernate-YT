package com.randy.springboot.learn.model.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.randy.springboot.learn.model.entities.Vendor;

public interface VendorRepo extends CrudRepository<Vendor, Long>{
    
    //Derived Query Methods 
    Vendor findByEmail(String email);

    List<Vendor> findByNameContainingOrderByIdDesc(String name);

    List<Vendor> findByNameContainingOrEmailContaining(String name, String email);
}

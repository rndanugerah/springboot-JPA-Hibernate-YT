package com.randy.springboot.learn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.randy.springboot.learn.model.entities.Vendor;
import com.randy.springboot.learn.model.repos.VendorRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VendorService {
    
    @Autowired
    private VendorRepo vendorRepo;

    public Vendor save(Vendor vendor){
        return vendorRepo.save(vendor);
    }

    public Vendor findOne(Long id){
        Optional<Vendor> vendor = vendorRepo.findById(id);
        if(!vendor.isPresent()){
            return null;
        }
        return vendorRepo.findById(id).get();
    }

    public Iterable<Vendor> findAll(){
        return vendorRepo.findAll();
    }

    public void removeOne(long id){
        vendorRepo.deleteById(id);
    }

    public Vendor findbyEmail(String email){
        return vendorRepo.findByEmail(email);
    }

    public List<Vendor> findbyName(String name){
        return vendorRepo.findByNameContainingOrderByIdDesc(name);
    }

    public List<Vendor> findbyNameorEmail(String name, String email){
        return vendorRepo.findByNameContainingOrEmailContaining(name, email);
    }
}

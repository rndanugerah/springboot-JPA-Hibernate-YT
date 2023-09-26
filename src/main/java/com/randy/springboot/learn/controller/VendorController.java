package com.randy.springboot.learn.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import com.randy.springboot.learn.dto.VendorDto;
import com.randy.springboot.learn.model.entities.Vendor;
import com.randy.springboot.learn.services.VendorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Vendor>> create(@Valid @RequestBody VendorDto vendorDto, Errors errors){
        
        ResponseData<Vendor> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // Vendor vendor = new Vendor();
        // vendor.setName(vendordto.getName());
        // vendor.setAddress(vendordto.getAddress());
        // vendor.setEmail(vendordto.getEmail());
        // diubah lebih ringkas kek dibawah

        Vendor vendor = modelMapper.map(vendorDto, Vendor.class);
        
        responseData.setStatus(true);
        responseData.setPayload(vendorService.save(vendor));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Vendor> findAll(){
        return vendorService.findAll();
    }

    @GetMapping("/{id}")
    public Vendor findOne(@PathVariable("id") long id){
        return vendorService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Vendor>> update(@Valid @RequestBody VendorDto vendorDto, Errors errors){
        
        ResponseData<Vendor> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Vendor vendor = modelMapper.map(vendorDto, Vendor.class);

        responseData.setStatus(true);
        responseData.setPayload(vendorService.save(vendor));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") long id){
        vendorService.removeOne(id);
    }

    @PostMapping("/search/byEmail")
    public Vendor findByEmail(@RequestBody SearchDto searchDto){
        return vendorService.findbyEmail(searchDto.getSearchKey());
    }

    @PostMapping("/search/byName")
    public List<Vendor> findbyName(@RequestBody SearchDto searchDto){
        return vendorService.findbyName(searchDto.getSearchKey());
    }

    @PostMapping("/search/byNameOrEmail")
    public List<Vendor> findbyNameorEmail(@RequestBody SearchDto searchDto){
        return vendorService.findbyNameorEmail(searchDto.getSearchKey(), searchDto.getSecondSearchKey());
    }
}

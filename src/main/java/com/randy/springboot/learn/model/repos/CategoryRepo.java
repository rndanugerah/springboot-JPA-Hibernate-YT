package com.randy.springboot.learn.model.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.randy.springboot.learn.model.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {    

    Page<Category> findByNameContaining(String name, Pageable pageable);
}

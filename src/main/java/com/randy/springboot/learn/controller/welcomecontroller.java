package com.randy.springboot.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class welcomecontroller {
    
    @GetMapping
    public String welcome(){
        return "hello world";
    }

}

package com.randy.springboot.learn.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.randy.springboot.learn.services.Course;

@RestController
public class CourseController {
    
    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses() {
        return Arrays.asList(
            new Course (1, "Learn Spring Boot", "randy"),
            new Course (2, "Learn JPA", "alfin"),
            new Course (3, "Learn Hibernate", "reza"),
            new Course (4, "Learn DevTools", "radit")
        );
    }
}

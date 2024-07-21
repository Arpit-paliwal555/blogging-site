package com.example.medium_backend.controller;

import com.example.medium_backend.dto.BlogDto;
import com.example.medium_backend.service.BlogServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "http://localhost:4200")
public class blogController {

    @Autowired
    BlogServiceImplementation blogServiceImplementation;

    @GetMapping
    List<BlogDto> getAllBlogs(){
        return blogServiceImplementation.getAllBlogs();
    }

    @GetMapping("/{id}")
    BlogDto getBlogById(@PathVariable int id){
        return blogServiceImplementation.getBlogById(id);
    }
    @PostMapping
    ResponseEntity<BlogDto> saveBlog(@RequestBody BlogDto blog){
        BlogDto savedBlog = blogServiceImplementation.createBlog(blog);
        LocalDate today = LocalDate.now();
        HttpStatus status;
        if(savedBlog != null){
            status = HttpStatus.CREATED;
        }else{
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(savedBlog);
    }

    @GetMapping("/user/{userId}")
    List<BlogDto> getAllBlogsByUserId(@PathVariable int userId){
        return blogServiceImplementation.getBlogsByUserid(userId);
    }
    @DeleteMapping("/delete/{blogId}")
    void deleteBlog(@PathVariable int blogId){
        blogServiceImplementation.deleteBlog(blogId);
    }
}

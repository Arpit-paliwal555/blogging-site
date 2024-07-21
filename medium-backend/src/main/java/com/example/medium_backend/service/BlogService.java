package com.example.medium_backend.service;

import com.example.medium_backend.dto.BlogDto;
import com.example.medium_backend.model.Blog;

import java.util.List;

public interface BlogService {

    List<BlogDto> getAllBlogs();

    BlogDto createBlog(BlogDto newBlog);

    BlogDto getBlogById(int id);

    List<BlogDto> getBlogsByUserid(int userId);

    void deleteBlog(int id);

    void updateBlog(int id);

}

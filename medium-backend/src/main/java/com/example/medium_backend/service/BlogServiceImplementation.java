package com.example.medium_backend.service;

import com.example.medium_backend.dto.BlogDto;
import com.example.medium_backend.model.Blog;
import com.example.medium_backend.repository.BlogRepository;
import com.example.medium_backend.utill.EntityToDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImplementation implements BlogService{

    private static final Logger log = LoggerFactory.getLogger(BlogServiceImplementation.class);
    private final BlogRepository blogRepository;

    public BlogServiceImplementation(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @Override
    public List<BlogDto> getAllBlogs() {
        return blogRepository.findAll().parallelStream().map(EntityToDto::convertToBlogDto).toList();
    }

    @Override
    public BlogDto createBlog(BlogDto newBlog) {
        return EntityToDto.convertToBlogDto(blogRepository.save(EntityToDto.convertToBlogEntity(newBlog)));

    }

    @Override
    public BlogDto getBlogById(int id) {
        Blog blog = blogRepository.getReferenceById(id);
        return EntityToDto.convertToBlogDto(blog);
    }

    @Override
    public void deleteBlog(int id) {
        try {
            blogRepository.deleteById(id);
        }catch (RuntimeException e){
            log.info("Record not found!");
        }

    }
    @Override
    public List<BlogDto> getBlogsByUserid(int id){
      return blogRepository.findAllByUserId(id).stream().map(EntityToDto::convertToBlogDto).toList();
    }

    @Override
    public void updateBlog(int id) {

    }
}

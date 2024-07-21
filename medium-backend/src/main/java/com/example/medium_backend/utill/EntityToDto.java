package com.example.medium_backend.utill;

import com.example.medium_backend.dto.BlogDto;
import com.example.medium_backend.dto.UserDto;
import com.example.medium_backend.model.Blog;
import com.example.medium_backend.model.User;

public class EntityToDto {
    public static Blog convertToBlogEntity(BlogDto blogDto){
        Blog blog = new Blog();
        blog.setBlogId(blogDto.id());
        blog.setTitle(blogDto.title());
        blog.setContent(blogDto.content());
        blog.setUserId(blogDto.userId());
        blog.setDate(blogDto.date());
        return blog;
    }

    public static BlogDto convertToBlogDto(Blog blog){
        return new BlogDto(blog.getBlogId(),blog.getTitle(),blog.getContent(), blog.getUserId(),blog.getDate());
    }

    public static User convertToUserEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.id());
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());

        return user;
    }

    public static UserDto convertToUserDto(User user){
        return new UserDto(user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),"USER");
    }
}

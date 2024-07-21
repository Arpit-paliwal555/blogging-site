package com.example.medium_backend.config;

import com.example.medium_backend.dto.UserDto;
import com.example.medium_backend.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.medium_backend.dto.CustomUserDetails;

@Component
public class UserInfoDetailsService implements UserDetailsService{
    @Autowired
    private UserServiceImplementation repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = repository.getUserByUsername(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetails(userDto);
    }
}

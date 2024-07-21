package com.example.medium_backend.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record UserDto(int id, String username, String email, String password, String role) {

}

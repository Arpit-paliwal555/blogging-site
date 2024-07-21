package com.example.medium_backend.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final UserDto userDto;



    public CustomUserDetails(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming UserDto has a list of roles or authorities
        return List.of(new SimpleGrantedAuthority(userDto.role()));
    }

    @Override
    public String getPassword() {
        return userDto.password();
    }

    @Override
    public String getUsername() {
        return userDto.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement logic if needed
    }
}

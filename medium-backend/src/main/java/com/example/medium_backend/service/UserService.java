package com.example.medium_backend.service;

import com.example.medium_backend.dto.UserDto;
import com.example.medium_backend.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto getUserById(int id);

    UserDto getUserByUsername(String name);

    void deleteUser(int id);

    void updateUser(int id);
}

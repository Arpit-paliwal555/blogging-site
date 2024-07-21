package com.example.medium_backend.service;

import com.example.medium_backend.dto.UserDto;
import com.example.medium_backend.model.User;
import com.example.medium_backend.repository.UserRepository;
import com.example.medium_backend.utill.EntityToDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().parallelStream().map(EntityToDto::convertToUserDto).toList();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
       return EntityToDto.convertToUserDto(userRepository.save(EntityToDto.convertToUserEntity(userDto)));
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userRepository.getReferenceById(id);
        return EntityToDto.convertToUserDto(user);
    }

    @Override
    public UserDto getUserByUsername(String name) {
        User user = userRepository.getUserByUsername(name);
        return EntityToDto.convertToUserDto(user);
    }

    @Override
    public void deleteUser(int id) {
        try {
            userRepository.deleteById(id);
        }catch (RuntimeException e){
            log.info("Record not found!");
        }

    }

    @Override
    public void updateUser(int id) {

    }
}

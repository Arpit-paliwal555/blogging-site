package com.example.medium_backend.controller;

import com.example.medium_backend.dto.AuthRequest;
import com.example.medium_backend.dto.UserDto;
import com.example.medium_backend.model.User;
import com.example.medium_backend.repository.UserRepository;
import com.example.medium_backend.service.JwtService;
import com.example.medium_backend.service.UserServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class userController {

    private static final Logger logger = LoggerFactory.getLogger(userController.class);

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    List<UserDto> getAllUsers(){
        return userServiceImplementation.getAllUsers();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable int id){
        return userServiceImplementation.getUserById(id);
    }

    @PostMapping("/signup")
    ResponseEntity<UserDto> saveUser(@RequestBody UserDto user){
        UserDto savedUserDto = userServiceImplementation.createUser(user);
        HttpStatus status;
        if(savedUserDto != null){
            status = HttpStatus.CREATED;
        }else{
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(savedUserDto);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable int id){
        userServiceImplementation.deleteUser(id);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        try {
            logger.info(authentication.getAuthorities().toString());
            if(authentication.isAuthenticated())
                return jwtService.generateToken(authRequest.getUsername());
            else
                throw new UsernameNotFoundException("invalid user request !");
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", authRequest.getUsername());
            throw new UsernameNotFoundException("invalid user request !");
        }
    }


    @PostMapping("/check")
    public String check(){
        return "Check";
    }



}

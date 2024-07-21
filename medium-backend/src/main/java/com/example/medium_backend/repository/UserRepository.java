package com.example.medium_backend.repository;

import com.example.medium_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User getUserByUsername(String name);
}

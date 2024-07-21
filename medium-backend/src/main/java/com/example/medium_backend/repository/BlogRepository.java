package com.example.medium_backend.repository;

import com.example.medium_backend.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Integer> {

    @Query("SELECT b FROM Blog b WHERE b.userId = :userId")
    List<Blog> findAllByUserId(@Param("userId") int userId);
}

package com.example.medium_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
    @Column(length = 510)
    private String title;

    @Column(length = 2040)
    private String content;

    @Column
    private Date date = Date.valueOf(LocalDate.now());

    @Column
    private int userId;

}

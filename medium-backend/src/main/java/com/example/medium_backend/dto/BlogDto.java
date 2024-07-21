package com.example.medium_backend.dto;

import java.sql.Date;
import java.time.LocalDate;

public record BlogDto(int id, String title, String content, int userId, Date date){

}

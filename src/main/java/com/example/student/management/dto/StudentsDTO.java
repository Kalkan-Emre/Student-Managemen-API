package com.example.student.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentsDTO {
    private String name;
    private Set<String> courses = new HashSet<String>();
    private String email;
    private LocalDate dob;
    private Long id;

    public StudentsDTO(String name, Set<String> courses, String email, LocalDate dob) {
        this.name = name;
        this.courses = courses;
        this.email = email;
        this.dob = dob;
    }

    public StudentsDTO(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
}

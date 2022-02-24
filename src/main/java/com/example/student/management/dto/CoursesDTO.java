package com.example.student.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDTO {
    private String name;
    private Integer capacity;
    private String teacher;
    private Set<Long> enrolledStudents = new HashSet<>();
    private Long id;

    public CoursesDTO(String name, Integer capacity, String instructor, Set<Long> enrolledStudents) {
        this.name = name;
        this.capacity = capacity;
        teacher = instructor;
        this.enrolledStudents = enrolledStudents;
    }

    public CoursesDTO(String name, Integer capacity, String teacher) {
        this.name = name;
        this.capacity = capacity;
        this.teacher = teacher;
    }
}

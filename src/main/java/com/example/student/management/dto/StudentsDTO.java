package com.example.student.management.dto;

import com.example.student.management.persistence.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
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

    public void addCourses(@NotNull Course course) {
        this.courses.add(course.getName());
    }
}

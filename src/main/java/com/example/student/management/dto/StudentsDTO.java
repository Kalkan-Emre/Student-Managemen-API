package com.example.student.management.dto;

import com.example.student.management.persistence.entity.Course;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    public StudentsDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getCourses() {
        return courses;
    }

    public void addCourses(Course course) {
        this.courses.add(course.getName());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

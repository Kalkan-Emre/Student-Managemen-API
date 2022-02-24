package com.example.student.management.dto;


import com.example.student.management.persistence.entity.Student;

import java.util.HashSet;
import java.util.Set;

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

    public CoursesDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String instructor) {
        teacher = instructor;
    }

    public Set<Long> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addEnrolledStudent(Student student) {
           this.enrolledStudents.add(student.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

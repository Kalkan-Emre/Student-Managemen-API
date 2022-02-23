package com.example.student.management.dto;


import com.example.student.management.persistence.entity.Courses;
import com.example.student.management.persistence.entity.Students;

import java.util.HashSet;
import java.util.Set;

public class CoursesDTO {
    private String name;
    private Integer capacity;
    private String Instructor;
    private Set<Long> enrolledStudents = new HashSet<>();
    private Long id;

    public CoursesDTO(String name, Integer capacity, String instructor, Set<Long> enrolledStudents) {
        this.name = name;
        this.capacity = capacity;
        Instructor = instructor;
        this.enrolledStudents = enrolledStudents;
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

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public Set<Long> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<Students> enrolledStudents) {
        for(var student: enrolledStudents){
            this.enrolledStudents.add(student.getId());
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

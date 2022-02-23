package com.example.student_managment.Courses;

import com.example.student_managment.Student.Students;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Courses {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Students> enrolledStudents = new HashSet<>();
    private String name;
    private Integer capacity;
    private String instructor;

    public Courses(String name, Integer capacity, String instructor) {
        this.name = name;
        this.capacity = capacity;
        this.instructor = instructor;
    }

    public Courses(Long id, String name, Integer capacity, String instructor) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.instructor = instructor;
    }

    public Courses() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Set<Students> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<Students> enrolled) {
        this.enrolledStudents = enrolled;
    }

    public void enrollStudent(Students student) {
        enrolledStudents.add(student);
    }
}

package com.example.student.management.persistence.repository;

import com.example.student.management.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo
        extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByEmail(String email);
}

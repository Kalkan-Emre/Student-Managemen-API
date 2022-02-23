package com.example.student_managment.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo
        extends JpaRepository<Students, Long> {

    Optional<Students> findStudentClassByEmail(String email);
}

package com.example.student.management.persistence.repository;

import com.example.student.management.persistence.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo
        extends JpaRepository<Students, Long> {

    Optional<Students> findStudentClassByEmail(String email);
}

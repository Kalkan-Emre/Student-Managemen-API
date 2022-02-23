package com.example.student.management.persistence.repository;

import com.example.student.management.persistence.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

    Optional<Courses> findCoursesByName(String name);
}

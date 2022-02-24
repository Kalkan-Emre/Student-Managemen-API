package com.example.student.management.persistence.repository;

import com.example.student.management.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCoursesByName(String name);
}

package com.example.student.management.service;

import com.example.student.management.dto.CoursesDTO;
import java.util.List;

public interface CourseService {

    List<CoursesDTO> getCourses();

    void saveCourse(CoursesDTO courseDto);

    void deleteCourse(Long id);

    long getCount();

    void update(long id, String name, Integer capacity, String teacher);

    void enrollStudent(Long courseId, Long studentId);
}

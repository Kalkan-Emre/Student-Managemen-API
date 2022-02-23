package com.example.student.management.mapper;

import com.example.student.management.dto.CoursesDTO;
import com.example.student.management.persistence.entity.Courses;

public interface CourseMapper {
    CoursesDTO mapEntityToDto(Courses course);
}

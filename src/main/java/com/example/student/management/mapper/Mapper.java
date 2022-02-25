package com.example.student.management.mapper;

import com.example.student.management.dto.CoursesDTO;
import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.persistence.entity.Course;
import com.example.student.management.persistence.entity.Student;

public interface Mapper {

    CoursesDTO mapEntityToDto(Course course);

    Course mapDtoToEntity(CoursesDTO coursesDto);

    StudentsDTO mapEntityToDto(Student student);

    Student mapDtoToEntity(StudentsDTO studentDto);
}

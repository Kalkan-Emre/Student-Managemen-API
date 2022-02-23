package com.example.student.management.mapper;

import com.example.student.management.dto.CoursesDTO;
import com.example.student.management.persistence.entity.Courses;
import org.springframework.stereotype.Component;

@Component
public class CourseMapperImpl implements CourseMapper{
    @Override
    public CoursesDTO mapEntityToDto(Courses course){
        var coursesDTO = new CoursesDTO();
        coursesDTO.setCapacity(course.getCapacity());
        coursesDTO.setInstructor(course.getTeacher());
        coursesDTO.setName(course.getName());
        coursesDTO.setEnrolledStudents(course.getEnrolledStudents());
        coursesDTO.setId(course.getId());
        return coursesDTO;
    }

    //TODO: Implement  mapDtoToEntity method
}

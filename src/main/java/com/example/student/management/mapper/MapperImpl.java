package com.example.student.management.mapper;

import com.example.student.management.dto.CoursesDTO;
import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.persistence.entity.Course;
import com.example.student.management.persistence.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper{
    @Override
    public CoursesDTO mapEntityToDto(Course course){
        var coursesDTO = new CoursesDTO();
        coursesDTO.setCapacity(course.getCapacity());
        coursesDTO.setTeacher(course.getTeacher());
        coursesDTO.setName(course.getName());
        course.getEnrolledStudents().forEach(coursesDTO::addEnrolledStudent);
        coursesDTO.setId(course.getId());
        return coursesDTO;
    }

    @Override
    public Course mapDtoToEntity(CoursesDTO coursesDTO){
        var course = new Course();
        course.setCapacity(coursesDTO.getCapacity());
        course.setTeacher(coursesDTO.getTeacher());
        course.setName(coursesDTO.getName());
        course.setId(coursesDTO.getId());
        return course;
    }

    @Override
    public StudentsDTO mapEntityToDto(Student student){
        var studentDto = new StudentsDTO();
        studentDto.setEmail(student.getEmail());
        studentDto.setDob(student.getDob());
        student.getCourses().forEach(studentDto::addCourses);
        studentDto.setName(student.getName());
        studentDto.setId(student.getId());
        return studentDto;
    }

    @Override
    public Student mapDtoToEntity(StudentsDTO studentDto){
        var student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setDob(studentDto.getDob());
        return student;
    }

}

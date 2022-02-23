package com.example.student.management.mapper;

import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.persistence.entity.Students;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentsDTO mapEntityToDto(Students student){
        var studentDto = new StudentsDTO();
        studentDto.setEmail(student.getEmail());
        studentDto.setDob(student.getDob());
        studentDto.setCourses(student.getSubjects());
        studentDto.setName(student.getName());
        studentDto.setId(student.getId());
        return studentDto;
    }

    @Override
    public Students mapDtoToEntity(StudentsDTO studentDto){
        var student = new Students();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setDob(studentDto.getDob());
        return student;
    }

}

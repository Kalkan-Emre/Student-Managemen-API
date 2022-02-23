package com.example.student.management.mapper;

import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.persistence.entity.Students;

public interface StudentMapper {
    StudentsDTO mapEntityToDto(Students student);

    Students mapDtoToEntity(StudentsDTO studentDto);
}

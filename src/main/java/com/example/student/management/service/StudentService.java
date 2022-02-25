package com.example.student.management.service;

import com.example.student.management.dto.StudentsDTO;

import java.util.List;

public interface StudentService {

    List<StudentsDTO> getStudents();

    long getCount();

    void add(StudentsDTO studentDTO);

    void delete(Long idOfStudentToBeDelete);

    void update(long id, String name, String email);

    List<Long> getNotEnrolledToAnyCourse();
}

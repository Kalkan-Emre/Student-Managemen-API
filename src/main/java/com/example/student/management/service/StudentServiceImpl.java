package com.example.student.management.service;

import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.mapper.Mapper;
import com.example.student.management.persistence.entity.Student;
import com.example.student.management.persistence.repository.CourseRepository;
import com.example.student.management.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("studentService")
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final Mapper mapper;

    @Override
    public List<StudentsDTO> getStudents() {
        return studentRepository.findAll().stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public long getCount() {
        var studentList = studentRepository.findAll();
        return studentList.size();
    }

    @Override
    public void add(StudentsDTO studentDTO) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(studentDTO.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        else{
            studentRepository.save(mapper.mapDtoToEntity(studentDTO));
        }
    }

    @Override
    public void delete(Long idOfStudentToBeDelete){
        boolean exists = studentRepository.existsById(idOfStudentToBeDelete);
        if(!exists){
            throw new IllegalStateException("Student with id "+idOfStudentToBeDelete+" does not exists");
        }
        else{
            var student = studentRepository.findById(idOfStudentToBeDelete);
            courseRepository.findAll().stream().forEach(
                    course -> course.getEnrolledStudents().remove(student)
            );
            studentRepository.deleteById(idOfStudentToBeDelete);
        }
    }

    @Override
    @Transactional
    public void update(long id, String name, String email) {
        boolean isEmailTaken = studentRepository.findStudentByEmail(email).isPresent();
        if (studentRepository.findById(id).isEmpty()) {
            throw new IllegalStateException("Student with id "+id+" not found");
        }
        if(name!=null && name.length()>0 && !name.equals(studentRepository.getById(id).getName())){
            studentRepository.getById(id).setName(name);
        }
        if(email!=null&&email.length()>0){
            if(isEmailTaken){
                throw new IllegalStateException("Email taken");
            }
            else{
                studentRepository.getById(id).setEmail(email);
            }
        }
    }

    @Override
    public List<Long> getNotEnrolledToAnyCourse() {
        var students = studentRepository.findAll();
        if (students == null || students.isEmpty()){
            return Collections.emptyList();
        }
        return students.stream()
                .filter(student -> student.getCourses()
                    .stream().noneMatch(course -> course.getEnrolledStudents().contains(student)))
                .map(Student::getId).collect(Collectors.toList());
    }
}
package com.example.student.management.service;

import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.mapper.Mapper;
import com.example.student.management.persistence.entity.Student;
import com.example.student.management.persistence.repository.StudentRepo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "students")
public class StudentServiceImpl implements StudentService{

    private final StudentRepo studentRepository;
    private final Mapper mapper;

    public StudentServiceImpl(StudentRepo studentRepository, Mapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    @Cacheable("students")
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
            throw new IllegalStateException("Student with id "+idOfStudentToBeDelete+"does not exists");
        }
        else{
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
        return studentRepository.findAll().stream().filter(student -> !student.getCourses()
                .stream().map(course -> course.getEnrolledStudents().stream()
                        .collect(Collectors.toList()).contains(student)).collect(Collectors.toList())
                .contains(true)).map(student1 -> student1.getId()).collect(Collectors.toList());
    }
}
package com.example.student.management.service;

import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.mapper.Mapper;
import com.example.student.management.persistence.entity.Student;
import com.example.student.management.persistence.repository.StudentRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepo studentRepository;
    private final Mapper mapper;

    public StudentService(StudentRepo studentRepository, Mapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    public List<StudentsDTO> getStudents() {
        return studentRepository.findAll().stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    public long getCount() {
        var studentList = studentRepository.findAll();
        return studentList.size();
    }

    public void add(StudentsDTO studentDTO) {
        Optional<Student> studentOptional = studentRepository
                .findStudentClassByEmail(studentDTO.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        else{
            studentRepository.save(mapper.mapDtoToEntity(studentDTO));
        }
    }
    public void delete(Long idOfStudentToBeDelete){
        boolean exists = studentRepository.existsById(idOfStudentToBeDelete);
        if(!exists){
            throw new IllegalStateException("Student with id "+idOfStudentToBeDelete+"does not exists");
        }
        else{
            studentRepository.deleteById(idOfStudentToBeDelete);
        }
    }

    @Transactional
    public void update(long id, String name, String email) {
        boolean isEmailTaken = studentRepository.findStudentClassByEmail(email).isPresent();
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

}
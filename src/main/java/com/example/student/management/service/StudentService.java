package com.example.student.management.service;

import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.mapper.StudentMapper;
import com.example.student.management.persistence.entity.Students;
import com.example.student.management.persistence.repository.StudentRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepo studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentsDTO> getStudents() {

        var studentList = studentRepository.findAll();
        var studentDtoList = new ArrayList<StudentsDTO>();
        for(var student: studentList){
            studentDtoList.add(studentMapper.mapEntityToDto(student));
        }
        return studentDtoList;
    }

    public long getCount() {
        var studentList = studentRepository.findAll();
        return studentList.size();
    }

    public void add(StudentsDTO studentDTO) {
        Optional<Students> studentOptional = studentRepository
                .findStudentClassByEmail(studentDTO.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        else{
            studentRepository.save(studentMapper.mapDtoToEntity(studentDTO));
        }
    }
    public void delete(Long idOfStudentToBeDelete){ //TODO implement by using DTO
        boolean exists = studentRepository.existsById(idOfStudentToBeDelete);
        if(!exists){
            throw new IllegalStateException("Student with id "+idOfStudentToBeDelete+"does not exists");
        }
        else{
            studentRepository.deleteById(idOfStudentToBeDelete);
        }
    }

    @Transactional
    public void update(long id, String name, String email) { //TODO implement by using DTO
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
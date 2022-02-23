package com.example.student_managment.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepo studentRepository;

    @Autowired
    public StudentService(StudentRepo studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Students> getStudents() {
        return studentRepository.findAll();
    }

    public Long getCount() {
        return studentRepository.count();
    }

    public void add(Students studentToBeAdd) {
        Optional<Students> studentOptional = studentRepository
                .findStudentClassByEmail(studentToBeAdd.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        else{
            studentRepository.save(studentToBeAdd);
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
        if (!studentRepository.findById(id).isPresent()) {
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
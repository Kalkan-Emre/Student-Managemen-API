package com.example.student.management.service;

import com.example.student.management.dto.CoursesDTO;
import com.example.student.management.mapper.Mapper;
import com.example.student.management.persistence.entity.Course;
import com.example.student.management.persistence.repository.CoursesRepository;
import com.example.student.management.persistence.repository.StudentRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursesService {

    private final CoursesRepository coursesRepository;
    private final StudentRepo studentRepository;
    private final Mapper mapper;

    public CoursesService(CoursesRepository coursesRepository, StudentRepo studentRepository, Mapper mapper) {
        this.coursesRepository = coursesRepository;
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    public List<CoursesDTO> getCourses() {
        return coursesRepository.findAll().stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    public void saveCourse(CoursesDTO courseDto) {
        coursesRepository.save(mapper.mapDtoToEntity(courseDto));
    }

    public void deleteCourse(Long id) {
        System.out.println(id);
        boolean exists = coursesRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student with id " +id +" does not exists");
        }
        else{
            coursesRepository.deleteById(id);
        }
    }

    public long getCount() {
        var courseList = coursesRepository.findAll();
        return courseList.size();
    }

    @Transactional
    public void update(long id, String name, Integer capacity, String teacher) {
        if(coursesRepository.existsById(id)){
            Course current = coursesRepository.getById(id);
            if(coursesRepository.findCoursesByName(name).isEmpty()){
                if(!name.equals(current.getName()) &&!name.equals("")){
                    coursesRepository.getById(id).setName(name);
                }
            }
            else{
                throw new IllegalStateException("This name is not valid");
            }
            if(capacity!=null){
                coursesRepository.getById(id).setCapacity(capacity);
            }
            if(teacher!=null){
                coursesRepository.getById(id).setTeacher(teacher);
            }
        }
        else{
            throw new IllegalStateException("Id not found");
        }
    }

    @Transactional
    public void enrollStudent(Long courseId, Long studentId) {
        if (coursesRepository.findById(courseId).isPresent())
        {
            if(studentRepository.findById(studentId).isPresent()){
                coursesRepository.findById(courseId).get().getEnrolledStudents().add(studentRepository.findById(studentId).get());
            }
            else{
                throw new IllegalStateException("Student with id "+studentId+" is not present");
            }
        }
        else{
            throw new IllegalStateException("Course with id "+courseId+" is not present");
        }
    }
}

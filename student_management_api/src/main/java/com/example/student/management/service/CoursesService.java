package com.example.student.management.service;

import com.example.student.management.dto.CoursesDTO;
import com.example.student.management.mapper.CourseMapper;
import com.example.student.management.persistence.entity.Courses;
import com.example.student.management.persistence.repository.CoursesRepository;
import com.example.student.management.persistence.repository.StudentRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoursesService {

    private final CoursesRepository coursesRepository;
    private final StudentRepo studentRepository;
    private final CourseMapper courseMapper;

    public CoursesService(CoursesRepository coursesRepository, StudentRepo studentRepository, CourseMapper courseMapper) {
        this.coursesRepository = coursesRepository;
        this.studentRepository = studentRepository;
        this.courseMapper = courseMapper;
    }

    public List<CoursesDTO> getCourses() {
        var courseList = coursesRepository.findAll();
        var courseDtoList = new ArrayList<CoursesDTO>();
        for(var course: courseList){
            courseDtoList.add(courseMapper.mapEntityToDto(course));
        }
        return courseDtoList;
    }

    public void saveCourse(Courses course) {
        coursesRepository.save(course);
    }

    public void deleteCourse(Long id) {  //TODO implement by using DTO
        System.out.println(id);
        boolean exists = coursesRepository.existsById(id);
        System.out.println(id);
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
    public void update(long id, String name, Integer capacity, String instructor) { //TODO implement by using DTO
        if(coursesRepository.existsById(id)){
            Courses current = coursesRepository.getById(id);
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
            if(instructor!=null){
                coursesRepository.getById(id).setTeacher(instructor);
            }
        }
        else{
            throw new IllegalStateException("Id not found");
        }
    }

    @Transactional
    public void enrollStudent(Long courseId, Long studentId) { //TODO implement by using DTO
        if (coursesRepository.findById(courseId).isPresent())
        {
            if(studentRepository.findById(studentId).isPresent()){
                coursesRepository.findById(courseId).get().enrollStudent(studentRepository.findById(studentId).get());
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

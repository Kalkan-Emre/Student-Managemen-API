package com.example.student.management.service;

import com.example.student.management.dto.CoursesDTO;
import com.example.student.management.mapper.Mapper;
import com.example.student.management.persistence.entity.Course;
import com.example.student.management.persistence.repository.CourseRepository;
import com.example.student.management.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("CourseService")
@RequiredArgsConstructor
public class CoursesServiceImpl implements CourseService{

    private final CourseRepository coursesRepository;
    private final StudentRepository studentRepository;
    private final Mapper mapper;

    @Override
    public List<CoursesDTO> getCourses() {
        return coursesRepository.findAll().stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void saveCourse(CoursesDTO courseDto) {

        coursesRepository.save(mapper.mapDtoToEntity(courseDto));
    }

    @Override
    public void deleteCourse(Long id) {
        boolean exists = coursesRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student with id " +id +" does not exists");
        }
        else{
            coursesRepository.deleteById(id);
        }
    }

    @Override
    public long getCount() {
        var courseList = coursesRepository.findAll();
        return courseList.size();
    }

    @Override
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

    @Override
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

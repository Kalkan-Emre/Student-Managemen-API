package com.example.student_managment.Courses;

import com.example.student_managment.Student.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CoursesService {
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private StudentRepo studentRepository;

    public List<Courses> getCourses() {
        return coursesRepository.findAll();
    }

    public void saveCourse(Courses course) {
        coursesRepository.save(course);
    }

    public void deleteCourse(Long id) {
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
        return coursesRepository.count();
    }

    @Transactional
    public void update(long id, String name, Integer capacity, String instructor) {
        if(coursesRepository.existsById(id)){
            Courses current = coursesRepository.getById(id);
            if(coursesRepository.findCoursesByName(name).isPresent()){
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
                coursesRepository.getById(id).setInstructor(instructor);
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

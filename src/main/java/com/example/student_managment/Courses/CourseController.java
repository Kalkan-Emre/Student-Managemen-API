package com.example.student_managment.Courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController //@RestController neden çalışmıyor?
@RequestMapping(path="api/courses")
public class CourseController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping
    public List<Courses> getCourses(){
        return coursesService.getCourses();
    }
    @GetMapping(path = "/count")
    public long getCount(){return coursesService.getCount();}
    @PostMapping
    public  void saveCourse(@RequestBody Courses course) {
        coursesService.saveCourse(course);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteCourse(@PathVariable String id){
        System.out.println(id);
        coursesService.deleteCourse(Long.parseLong(id));
    }
    @PutMapping(path = "/update/{id}")
    public void updateCourse(@PathVariable String id,
                             @RequestParam(required=false) String name,
                             @RequestParam(required = false) Integer capacity,
                             @RequestParam(required = false) String instructor){
        coursesService.update(Long.parseLong(id),name,capacity,instructor);
    }
    @PutMapping(path = "/enroll-student-to-course/course/{courseId}/student/{studentId}")
    public void enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId){
        coursesService.enrollStudent(courseId, studentId);
    }
}

package com.example.student.management.resource;

import com.example.student.management.service.CoursesService;
import com.example.student.management.dto.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController //@RestController neden çalışmıyor?
@RequestMapping(path="api/courses")
public class CourseController {
    @Autowired
    private CoursesService coursesService;

    @GetMapping
    public List<CoursesDTO> getCourses(){
        return coursesService.getCourses();
    }
    @GetMapping(path = "/count")
    public long getCount(){return coursesService.getCount();}
    @PostMapping
    public  void saveCourse(@RequestBody CoursesDTO courseDto) {
        coursesService.saveCourse(courseDto);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteCourse(@PathVariable String id){
        coursesService.deleteCourse(Long.parseLong(id));
    }
    @PutMapping(path = "/update/{id}")
    public void updateCourse(@PathVariable String id,
                             @RequestParam(required=false) String name,
                             @RequestParam(required = false) Integer capacity,
                             @RequestParam(required = false) String teacher){
        coursesService.update(Long.parseLong(id),name,capacity,teacher);
    }
    @PutMapping(path = "/enroll-student-to-course/course/{courseId}/student/{studentId}")
    public void enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId){
        coursesService.enrollStudent(courseId, studentId);
    }
}

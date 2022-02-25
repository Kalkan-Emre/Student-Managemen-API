package com.example.student.management.resource;

import com.example.student.management.service.CourseService;
import com.example.student.management.dto.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController //@RestController neden çalışmıyor?
@RequestMapping(path="api/courses")
public class CourseController {

    @Qualifier("CourseCacheServiceImplComposition")
    @Autowired
    private CourseService coursesService;

    @GetMapping
    public List<CoursesDTO> getCourses(){
        return coursesService.getCourses();
    }
    @GetMapping(path = "/count")
    public long getCount(){return coursesService.getCount();}
    @PostMapping
    public  void saveCourse(@RequestParam String name,
                            @RequestParam Integer capacity,
                            @RequestParam String teacher) {
        var courseDto = new CoursesDTO(name, capacity, teacher);
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

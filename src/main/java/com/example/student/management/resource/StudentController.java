package com.example.student.management.resource;

import com.example.student.management.dto.StudentsDTO;
import com.example.student.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    @Qualifier("studentCacheCompositionService")
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentsDTO> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "/count")
    public Long getCount(){
        return studentService.getCount();
    }

    @PostMapping(path = "/add")
    public void add(@RequestBody StudentsDTO student){
        studentService.add(student);
    }

    @DeleteMapping(path="/delete/{id}")
    public void delete(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        studentService.delete((long) intId);
    }

    @PutMapping(path = "/{id}")
    public void updateStudent(@PathVariable String id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        long intId = Integer.parseInt((id));
        studentService.update(intId,name,email);
    }
    @GetMapping(path = "/not-enrolled")
    public List<Long> getNotEnrolled(){
        return studentService.getNotEnrolledToAnyCourse();
    }
}

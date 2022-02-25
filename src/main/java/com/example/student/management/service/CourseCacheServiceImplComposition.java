package com.example.student.management.service;

import com.example.student.management.dto.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("CourseCacheServiceImplComposition")
public class CourseCacheServiceImplComposition implements CourseService{


    @Qualifier("CourseService")
    @Autowired
    private CourseService dbCourseService;

    private List<CoursesDTO> cacheStudents = new ArrayList<>();
    private static LocalDateTime lastRefreshTime = LocalDateTime.now();
    private static final int REFRESH_TIME_INTERVAL_IN_MINUTES = 1;

    @Override
    public List<CoursesDTO> getCourses() {
        if (lastRefreshTime.plusMinutes(REFRESH_TIME_INTERVAL_IN_MINUTES).isBefore(LocalDateTime.now())){
            cacheStudents = dbCourseService.getCourses();
            lastRefreshTime = LocalDateTime.now();
        }
        return cacheStudents;
    }

    @Override
    public void saveCourse(CoursesDTO courseDto) {
        dbCourseService.saveCourse(courseDto);
    }

    @Override
    public void deleteCourse(Long id) {
        dbCourseService.deleteCourse(id);
    }

    @Override
    public long getCount() {
        return dbCourseService.getCount();
    }

    @Override
    public void update(long id, String name, Integer capacity, String teacher) {
        dbCourseService.update(id, name, capacity, teacher);
    }

    @Override
    public void enrollStudent(Long courseId, Long studentId) {
        dbCourseService.enrollStudent(courseId, studentId);
    }
}

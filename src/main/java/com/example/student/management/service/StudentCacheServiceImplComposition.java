package com.example.student.management.service;

import com.example.student.management.dto.StudentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("studentCacheCompositionService")
public class StudentCacheServiceImplComposition implements StudentService {

    @Qualifier("studentService")
    @Autowired
    private StudentService dbStudentService;

    private List<StudentsDTO> cacheStudents = new ArrayList<>();
    private static LocalDateTime lastRefreshTime = LocalDateTime.now();
    private static final int REFRESH_TIME_INTERVAL_IN_MINUTES = 1;

    @Override
    public List<StudentsDTO> getStudents() {
        if (lastRefreshTime.plusMinutes(REFRESH_TIME_INTERVAL_IN_MINUTES).isBefore(LocalDateTime.now())){
            cacheStudents = dbStudentService.getStudents();
            lastRefreshTime = LocalDateTime.now();
        }
        return cacheStudents;
    }
    @Override
    public long getCount() {
        return dbStudentService.getCount();
    }

    @Override
    public void add(StudentsDTO studentDTO) {
        dbStudentService.add(studentDTO);
    }

    @Override
    public void delete(Long idOfStudentToBeDelete) {
        dbStudentService.delete(idOfStudentToBeDelete);
    }

    @Override
    public void update(long id, String name, String email) {
        dbStudentService.update(id, name, email);
    }

    @Override
    public List<Long> getNotEnrolledToAnyCourse() {
        return dbStudentService.getNotEnrolledToAnyCourse();
    }

}

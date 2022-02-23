
package com.example.student_managment.Student;

import com.example.student_managment.Courses.CoursesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepository, CoursesRepository coursesRepository){
        return args -> {
            Students emre = new Students(
                    "emre",
                    "emre@mail.com",
                    LocalDate.of(2001, OCTOBER, 11)
            );

            Students goksu = new Students(
                    "goksu",
                    "göksu@mail.com",
                    LocalDate.of(2006, APRIL, 18)
            );

            studentRepository.saveAll(
                    List.of(emre,goksu)
            );
        };
    }
}

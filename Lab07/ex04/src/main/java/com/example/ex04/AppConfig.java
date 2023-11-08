package com.example.ex04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {
    @Autowired
    StudentRepository repository;
    @Bean
    public CommandLineRunner runner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("Student have age greater than or equal 10:");
                List<Student> students = repository.ReadAgeGreaterThanEqual(12);
                printStudent(students);
                System.out.println("<===================================>");
                System.out.println("Count number of students whose ielts score is equal to 8.0: "
                        + repository.countByIeltsScoreEquals(8.0));
                System.out.println("<===================================>");
                System.out.println("Students whose name contains the word 'LOng': ");
                List<Student> studentsContain = repository.findByNameContains("LOng");
                printStudent(studentsContain);
            }
        };

    }
    public static void printStudent(List<Student> students){
        students.forEach(student -> {
            System.out.println(student.toString());
        });
    }
}

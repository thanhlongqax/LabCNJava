package com.example.ex05;

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
                List<Student> students = repository.getByAgeGreaterThanEqual(12);
                printStudent(students);
                System.out.println("<===================================>");
                System.out.println("Count number of students whose ielts score is equal to 8.0: "
                        + repository.getCountByIeltsScoreEquals(8.0));
                System.out.println("<===================================>");
                System.out.println("Students whose name contains the word 'LOng': ");
                List<Student> studentsContain = repository.getByNameContaining("LOng");
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

package com.example.ex03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Autowired
	StudentRepository studentRepository;

	Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class,args);
	}

	@Bean
	public CommandLineRunner Runner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				// Add at least 3 students to the database
				Student student1 = new Student(1,"John", 20, "john@example.com", 7.5);
				Student student2 = new Student(2,"Alice", 22, "alice@example.com", 6.8);
				Student student3 = new Student(3,"Bob", 21, "bob@example.com", 6.2);
				logger.info("<==============================>");
				logger.info("EXERCISE 3: ");
				studentRepository.save(student1);
				studentRepository.save(student2);
				studentRepository.save(student3);
				// Read the student list and print it to the console
				Iterable<Student> students = studentRepository.findAll();
				System.out.println("Student List:");
				students.forEach(System.out::println);

				// Update a student's information
				Student studentToUpdate = studentRepository.findById(1L).orElse(null);
				if (studentToUpdate != null) {
					studentToUpdate.setAge(21);
					studentRepository.save(studentToUpdate);
				}

				// Print the updated student list
				System.out.println("Updated Student List:");
				students = studentRepository.findAll();
				students.forEach(System.out::println);

				// Delete a student from the database
				studentRepository.deleteById(2L);

				// Print the student list after deletion
				System.out.println("Student List after Deletion:");
				students = studentRepository.findAll();
				students.forEach(System.out::println);
			}

		};
	}
}

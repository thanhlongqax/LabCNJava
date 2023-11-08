package com.example.ex02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ex02Application {


	private final StudentRepository studentRepository;

	public Ex02Application(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Ex02Application.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Student student1 = new Student(2,"John", 20, "john@example.com", 7.5);
				Student student2 = new Student(1,"Alice", 22, "alice@example.com", 6.8);
				Student student3 = new Student(3,"Bob", 21, "bob@example.com", 6.2);

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

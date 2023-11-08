package com.example.ex06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class Application {
	@Autowired
	private StudentRepository repository;

	public static void main(String[] args) {
		Application app = SpringApplication
				.run(Application.class, args)
				.getBean(Application.class);

		app.doExercise();
	}

	private void doExercise(){
		readStudentsSortedByAgeAndIelts();
		read456Students();
	}

	private void readStudentsSortedByAgeAndIelts(){
		System.out.println("<============================>");
		System.out.println("Students sorted by age and ielts score:");

		List<Student> studentsSortedByAge = (List<Student>) repository.findAll(
				Sort.by("age")
						.descending()
						.and(Sort.by("ieltsScore"))
		);

		printStudents(studentsSortedByAge);
	}

	private void read456Students(){
		System.out.println("<============================>");
		System.out.println("Students 4, 5, 6:");

		Page<Student> studentPage = repository.findAll(
				PageRequest.of(1, 3)
		);

		List<Student> students456 = studentPage.getContent();
		printStudents(students456);
	}

	private static void printStudents(List<Student> students){
		students.forEach(student -> {
			System.out.println(student.toString());
		});
	}
}

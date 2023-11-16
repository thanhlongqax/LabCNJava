package com.example.ex02.config;

import com.example.ex02.Model.Employee;
import com.example.ex02.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Autowired
    EmployeeRepository employeeRepository;

    @Bean
    public CommandLineRunner runner(){
        return new CommandLineRunner(){
            @Override
            public void run(String... args) throws Exception {
                if(!employeeRepository.existsByName("Thomas Hardy") && !employeeRepository.existsByName("Dominique Perrier")) {
                    Employee employee1 = new Employee();
                    employee1.setName("Thomas Hardy");
                    employee1.setEmail("thomashardy@mail.com");
                    employee1.setAddress("89 Chiaroscuro Rd, Portland, USA");
                    employee1.setPhone("(171) 555-2222");
                    employeeRepository.save(employee1);

                    Employee employee2 = new Employee();
                    employee2.setName("Dominique Perrier");
                    employee2.setEmail("thomashardy@mail.com");
                    employee2.setAddress("Obere Str. 57, Berlin, Germany");
                    employee2.setPhone("(313) 555-5735");
                    employeeRepository.save(employee2);
                }
            }
        };
    }
}

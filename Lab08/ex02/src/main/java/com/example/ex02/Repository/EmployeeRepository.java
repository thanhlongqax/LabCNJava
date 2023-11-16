package com.example.ex02.Repository;

import com.example.ex02.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public boolean existsByName(String name);
}

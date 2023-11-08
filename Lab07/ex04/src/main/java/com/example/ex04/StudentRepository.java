package com.example.ex04;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Queue;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> ReadAgeGreaterThanEqual(int x);
    long countByIeltsScoreEquals(double x);
    List<Student> findByNameContains(String xxx);
}
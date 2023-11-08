package com.example.ex05;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.age >= :x")
    List<Student> getByAgeGreaterThanEqual(@Param("x") int x);
    @Query("SELECT count(s) FROM Student s WHERE s.coreIELTs = :x")
    long getCountByIeltsScoreEquals(@Param("x") double x);
    @Query("SELECT s FROM Student s WHERE s.name like %:x%")
    List<Student> getByNameContaining(@Param("x") String xxx);
}
package com.example.ex03;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student implements Serializable {
    //id, name, age, email and ielts score attributes

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private  String email;
    private  double coreIELTs;
}

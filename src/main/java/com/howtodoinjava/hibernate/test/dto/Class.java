package com.howtodoinjava.hibernate.test.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@ToString
@Getter
@Setter
@Entity
@Table(name = "Class", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Id")})
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Integer classId;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "profile", nullable = false)
    private String profile;


    @OneToMany
    List<Student> studentList = new ArrayList<>();

    @OneToOne
    Professor professor;
    /*Id
    Name - String
    Profile – String

    @OneToMany
    List<Students>
*/



}

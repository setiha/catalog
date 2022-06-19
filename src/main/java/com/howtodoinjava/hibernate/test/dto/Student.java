package com.howtodoinjava.hibernate.test.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
@ToString
@Table(name = "Student", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Id")})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Integer studentId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;


    @Column(name = "grade", nullable = false)
    private Double grade;

    @ManyToMany
    private List<Reward> rewards;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return firstName.equals(student.firstName) && lastName.equals(student.lastName) && grade.equals(student.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, grade);
    }

/*ID
    FirstName -String
    LastName - String
    Grade – double*/


}

package com.howtodoinjava.hibernate.test.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
    @Setter
    @ToString
    @Entity
    @Table(name = "Professor", uniqueConstraints = {
            @UniqueConstraint(columnNames = "ID")
    })
    public class Professor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Integer id;
        @Column(name = "FIRST_NAME")
        private String firstName;
        @Column(name = "LAST_NAME")
        private String lastName;
        @Column(name = "EXPERIENCE")
        private Integer experience;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return firstName.equals(professor.firstName) && lastName.equals(professor.lastName) && experience.equals(professor.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, experience);
    }


}

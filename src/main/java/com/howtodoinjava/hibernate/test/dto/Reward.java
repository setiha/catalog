package com.howtodoinjava.hibernate.test.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Reward", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Id")})

public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Integer rewardId;
    @Column(name = "Name")
    private String name;

    @ManyToMany
    private List<Student> students;

}

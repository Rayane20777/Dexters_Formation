package com.example.dexters_formation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int roomNumber;

    @OneToOne
    private Instructor instructor;

    @OneToMany
    private List<Learner> learners;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

}

package com.example.dexters_formation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classes {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int roomNumber;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(mappedBy = "eclass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Learner> learners;

    @OneToOne
    @JoinColumn(name = "instructor_id", nullable = true)
    private Instructor instructor;
}
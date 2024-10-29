package com.example.dexters_formation.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Learner extends User {
    @Column
    private String level;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;
}
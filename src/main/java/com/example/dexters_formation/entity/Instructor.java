package com.example.dexters_formation.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends User {
    @Column
    private String speciality;

    @OneToOne(mappedBy = "instructor", optional = true)
    private Classes classes;
}
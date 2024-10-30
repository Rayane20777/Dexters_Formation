package com.example.dexters_formation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructor")
public class Instructor extends User {
    @Column
    private String speciality;

    @OneToOne(mappedBy = "instructor")
    @JsonManagedReference("instructor-classes")
    private Classes classes;
}
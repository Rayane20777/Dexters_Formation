package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Instructor;

import java.util.List;
import java.util.UUID;

public interface InstructorService {
    public Instructor create(Instructor instructor);
    List<Instructor> getAll();
    Instructor getById(UUID id);
}

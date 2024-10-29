package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Classes;

import java.util.List;
import java.util.UUID;

public interface ClassesService {
    Classes create(Classes classes);
    List<Classes> getAll();
    Classes getById(UUID id);
    Classes update(UUID id, Classes classes);
}

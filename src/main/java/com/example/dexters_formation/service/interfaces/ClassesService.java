package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Classes;

import java.util.List;

public interface ClassesService {
    Classes create(Classes classes);
    List<Classes> getAll();
}

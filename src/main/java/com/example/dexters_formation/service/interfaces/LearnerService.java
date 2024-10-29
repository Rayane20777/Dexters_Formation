package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Learner;

import java.util.List;
import java.util.UUID;

public interface LearnerService {
    Learner create(Learner learner);
    List<Learner> getAll();
    Learner getById(UUID id);
    Learner update(UUID id, Learner learner);
    void delete(UUID id);

}

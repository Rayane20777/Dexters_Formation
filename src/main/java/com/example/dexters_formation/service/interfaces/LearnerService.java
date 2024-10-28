package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Learner;

import java.util.List;

public interface LearnerService {
    Learner create(Learner learner);
    List<Learner> getAll();

}

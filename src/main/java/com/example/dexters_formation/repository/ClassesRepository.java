package com.example.dexters_formation.repository;

import com.example.dexters_formation.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, UUID> {
}

package com.example.diseaseapi.repositories;

import com.example.diseaseapi.models.entities.Symptom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SymptomRepository extends CrudRepository<Symptom, Integer> {
  Optional<Symptom> findByValue(String value);
}

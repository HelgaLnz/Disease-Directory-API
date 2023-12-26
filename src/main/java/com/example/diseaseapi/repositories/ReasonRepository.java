package com.example.diseaseapi.repositories;

import com.example.diseaseapi.models.entities.Reason;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReasonRepository extends CrudRepository<Reason, Integer> {

  Optional<Reason> findByValue(String value);
}

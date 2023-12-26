package com.example.diseaseapi.repositories;

import com.example.diseaseapi.models.entities.DiagnosticItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiagnosticItemRepository extends CrudRepository<DiagnosticItem, Integer> {

  Optional<DiagnosticItem> findByValue(String value);
}

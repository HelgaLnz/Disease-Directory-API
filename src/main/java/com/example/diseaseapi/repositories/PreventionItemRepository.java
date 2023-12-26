package com.example.diseaseapi.repositories;

import com.example.diseaseapi.models.entities.PreventionItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PreventionItemRepository extends CrudRepository<PreventionItem, Integer> {
  Optional<PreventionItem> findByValue(String value);
}

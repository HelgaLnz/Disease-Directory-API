package com.example.diseaseapi.repositories;

import com.example.diseaseapi.models.entities.RiskItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RiskItemRepository extends CrudRepository<RiskItem, Integer> {
  Optional<RiskItem> findByValue(String value);
}

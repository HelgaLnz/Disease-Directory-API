package com.example.diseaseapi.repositories;

import com.example.diseaseapi.models.entities.Disease;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiseaseRepository extends CrudRepository<Disease, Integer> {

  @Query("select d from Disease d where upper(d.name) ilike upper(concat('%', ?1, '%'))")
  Iterable<Disease> getDiseaseByNameContainingIgnoreCase(String name);

  @Query("select d from Disease d where d.name ilike concat(?1, '%')")
  Iterable<Disease> searchByNameStartingWith(String name);
}

package com.example.diseaseapi.repositories;

import com.example.diseaseapi.models.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}

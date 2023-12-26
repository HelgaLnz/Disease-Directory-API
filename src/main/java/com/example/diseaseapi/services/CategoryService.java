package com.example.diseaseapi.services;

import com.example.diseaseapi.models.entities.Category;
import com.example.diseaseapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Iterable<Category> getAll() {
    return categoryRepository.findAll();
  }
}

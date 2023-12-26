package com.example.diseaseapi.controllers;

import com.example.diseaseapi.models.entities.Category;
import com.example.diseaseapi.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Operation(summary = "Получения разделов заболеваний")
  @GetMapping("/categories")
  public Iterable<Category> getAll() {
    return categoryService.getAll();
  }
}

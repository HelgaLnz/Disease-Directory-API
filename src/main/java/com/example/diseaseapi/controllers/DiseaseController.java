package com.example.diseaseapi.controllers;

import com.example.diseaseapi.models.entities.Disease;
import com.example.diseaseapi.models.requests.DiseaseRequest;
import com.example.diseaseapi.services.DiseaseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class DiseaseController {

  private final DiseaseService diseaseService;

  @Operation(summary = "Получение списка заболеваний")
  @GetMapping("/diseases")
  public Iterable<Disease> getAll() {
    return diseaseService.getAll();
  }

  @Operation(summary = "Получение заболевания по id")
  @GetMapping("/diseases/{id}")
  public ResponseEntity<Disease> getById(@PathVariable int id) {
    Disease disease = diseaseService.getById(id);
    return new ResponseEntity<>(disease, HttpStatus.OK);
  }

  @Operation(summary = "Получение заболевания по названию")
  @GetMapping("/diseases/name/{name}")
  @SneakyThrows
  public ResponseEntity<Iterable<Disease>> getByNameContaining(@PathVariable String name) {
    return new ResponseEntity<>(diseaseService.getByNameContainingIgnoreCase(name), HttpStatus.OK);
  }

  @Operation(summary = "Получение заболевания начинающегося на входящий параметр")
  @GetMapping("/diseases/name/start/{name}")
  @SneakyThrows
  public ResponseEntity<Iterable<Disease>> getByNameStartWith(@PathVariable String name) {
    return new ResponseEntity<>(diseaseService.getByNameStartWith(name), HttpStatus.OK);
  }

  @Operation(summary = "Создание заболевания")
  @PostMapping("/diseases")
  @SneakyThrows
  public ResponseEntity<?> create(@RequestBody DiseaseRequest diseaseRequest) {
    diseaseService.create(diseaseRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Operation(summary = "Изменение заболевания")
  @PutMapping("/diseases/{id}")
  @SneakyThrows
  public ResponseEntity<?> update(@PathVariable int id, @RequestBody Disease disease) {
    diseaseService.update(id, disease);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Operation(summary = "Удаление заболевания")
  @DeleteMapping("/diseases/{id}")
  @SneakyThrows
  public ResponseEntity<?> delete(@PathVariable int id) {
    diseaseService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Operation(summary = "Добавление иллюстрации к заболеванию")
  @PostMapping("/diseases/upload-image/{id}")
  @SneakyThrows
  public ResponseEntity<?> uploadImage(
    @PathVariable int id,
    @RequestBody MultipartFile file
  ) {
    diseaseService.uploadImage(id, file);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}

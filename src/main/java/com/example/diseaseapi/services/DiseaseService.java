package com.example.diseaseapi.services;

import com.example.diseaseapi.handler.exceptions.DiseaseNotFoundException;
import com.example.diseaseapi.models.entities.*;
import com.example.diseaseapi.models.requests.DiseaseRequest;
import com.example.diseaseapi.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DiseaseService {

  private final DiseaseRepository diseaseRepository;
  private final CategoryRepository categoryRepository;
  private final DoctorSpecialityRepository doctorSpecialityRepository;
  private final DiagnosticItemRepository diagnosticItemRepository;
  private final PreventionItemRepository preventionItemRepository;
  private final ReasonRepository reasonRepository;
  private final RiskItemRepository riskItemRepository;
  private final SymptomRepository symptomRepository;

  @SneakyThrows
  public Iterable<Disease> getByNameContainingIgnoreCase(String name) {
    return diseaseRepository
      .getDiseaseByNameContainingIgnoreCase(name);
  }

  @SneakyThrows
  public Iterable<Disease> getByNameStartWith(String name) {
    return diseaseRepository
      .searchByNameStartingWith(name);
  }

  @SneakyThrows
  public Disease getById(int id) {
    return diseaseRepository
      .findById(id)
      .orElseThrow(DiseaseNotFoundException::new);
  }

  public Iterable<Disease> getAll() {
    return diseaseRepository.findAll();
  }

  @Transactional
  public void create(DiseaseRequest diseaseRequest) {
    CompletableFuture<Category> categoryCompletableFuture = CompletableFuture.supplyAsync(() ->
      categoryRepository
      .findById(diseaseRequest.getCategoryId())
      .orElse(null));

    CompletableFuture<DoctorSpeciality> doctorSpecialityCompletableFuture = CompletableFuture.supplyAsync(() ->
      doctorSpecialityRepository
      .findById(diseaseRequest.getDoctorSpecialityId())
      .orElse(null));

    CompletableFuture<List<Reason>> reasonsCompletableFuture = CompletableFuture.supplyAsync(() ->
      diseaseRequest
        .getReasons()
        .stream()
        .map(value ->
          reasonRepository.findByValue(value)
            .orElse(
              reasonRepository.save(
                Reason.builder()
                  .value(value)
                  .build()
              )
            )
        )
        .toList()
      );

    CompletableFuture<List<DiagnosticItem>> diagnosticGroupCompletableFuture = CompletableFuture.supplyAsync(() ->
      diseaseRequest
        .getDiagnostic()
        .stream()
        .map(value ->
          diagnosticItemRepository.findByValue(value)
            .orElse(
              diagnosticItemRepository.save(
                DiagnosticItem.builder()
                  .value(value)
                  .build()
              )))
        .toList()
    );

    CompletableFuture<List<PreventionItem>> preventionGroupCompletableFuture = CompletableFuture.supplyAsync(() ->
      diseaseRequest
        .getPreventions()
        .stream()
        .map(value ->
          preventionItemRepository.findByValue(value)
            .orElse(
              preventionItemRepository.save(
                PreventionItem.builder()
                  .value(value)
                  .build()
            )))
        .toList()
    );

    CompletableFuture<List<RiskItem>> riskGroupItemCompletableFuture = CompletableFuture.supplyAsync(() ->
      diseaseRequest
        .getRiskGroup()
        .stream()
        .map(value -> riskItemRepository.findByValue(value).orElse(
          RiskItem.builder()
            .value(value)
            .build()
        ))
        .toList()
    );

    CompletableFuture<List<Symptom>> symptomGroupItemCompletableFuture = CompletableFuture.supplyAsync(() ->
      diseaseRequest
        .getSymptoms()
        .stream()
        .map(value -> symptomRepository.findByValue(value).orElse(
          Symptom.builder()
            .value(value)
            .build()
        ))
        .toList()
    );

    Disease disease = new Disease();
    disease.setName(diseaseRequest.getName());
    disease.setDefinition(diseaseRequest.getDefinition());
    disease.setTherapy(diseaseRequest.getTherapy());
    disease.setDanger(diseaseRequest.getDanger());
    disease.setCategory(categoryCompletableFuture.join());
    disease.setDoctorSpeciality(doctorSpecialityCompletableFuture.join());
    disease.setReasons(reasonsCompletableFuture.join());
    disease.setDiagnostic(diagnosticGroupCompletableFuture.join());
    disease.setPreventions(preventionGroupCompletableFuture.join());
    disease.setRiskGroup(riskGroupItemCompletableFuture.join());
    disease.setSymptoms(symptomGroupItemCompletableFuture.join());

    diseaseRepository.save(disease);
  }

  public void delete(int id) {
    diseaseRepository.deleteById(id);
  }

  public Disease update(int id, Disease newDisease) {
    Disease disease = diseaseRepository.findById(id).orElse(null);

    if (disease != null) {
      disease = new Disease(
        newDisease.getId(),
        newDisease.getName(),
        newDisease.getDefinition(),
        newDisease.getReasons(),
        newDisease.getSymptoms(),
        newDisease.getTherapy(),
        newDisease.getClassification(),
        newDisease.getDanger(),
        newDisease.getRiskGroup(),
        newDisease.getDiagnostic(),
        newDisease.getPreventions(),
        newDisease.getDoctorSpeciality(),
        newDisease.getCategory(),
        new byte[0]
      );
      diseaseRepository.save(disease);
    }
    return disease;
  }

  public Disease uploadImage(int id, MultipartFile file) {
    Disease disease = diseaseRepository.findById(id).orElse(null);

    if (disease != null) {
      try {
        disease.setImage(file.getBytes());
        diseaseRepository.save(disease);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return disease;
  }
}

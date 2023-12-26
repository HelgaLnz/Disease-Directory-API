package com.example.diseaseapi.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiseaseRequest {
  private String name;

  private String definition;

  private List<String> reasons;

  private List<String> symptoms;

  private String therapy;

  private String classification;

  private String danger;

  private List<String> riskGroup;

  private List<String> diagnostic;

  private List<String> preventions;

  private int doctorSpecialityId;

  private int categoryId;
}

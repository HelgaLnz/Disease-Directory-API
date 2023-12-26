package com.example.diseaseapi.handler.exceptions;


public class DiseaseNotFoundException extends RuntimeException {
  public DiseaseNotFoundException() {
    super("Disease is not found.");
  }
}

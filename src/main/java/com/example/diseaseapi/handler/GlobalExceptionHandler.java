package com.example.diseaseapi.handler;

import com.example.diseaseapi.handler.exceptions.DiseaseNotFoundException;
import com.example.diseaseapi.models.responses.ExceptionResponse;
import org.springdoc.api.ErrorMessage;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler{

  @ExceptionHandler(DiseaseNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleDiseaseNotFound(DiseaseNotFoundException exception) {
    return ResponseEntity
      .status(NOT_FOUND)
      .body(new ErrorMessage(exception.getMessage()));
  }

  @ExceptionHandler(ApplicationContextException.class)
  public ResponseEntity<ErrorMessage> handleApplicationContextException(ApplicationContextException exception) {
    return ResponseEntity
      .status(INTERNAL_SERVER_ERROR)
      .body(new ErrorMessage(exception.getMessage()));
  }
}

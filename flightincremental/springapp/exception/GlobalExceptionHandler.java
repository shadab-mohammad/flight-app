package com.examly.springapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(SeatsExceededException.class)
    public ResponseEntity<String> handleSeatsExceededException(SeatsExceededException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<String> handleInvalidDateException(InvalidDateException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(DuplicateFlightException.class)
    public ResponseEntity<String> handleDuplicateFlightException(DuplicateFlightException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}

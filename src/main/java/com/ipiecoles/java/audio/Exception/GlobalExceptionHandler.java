package com.ipiecoles.java.audio.Exception;
import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
	public class GlobalExceptionHandler {
		 @ExceptionHandler(EntityNotFoundException.class)
		 @ResponseStatus(HttpStatus.NOT_FOUND)
		 public String handleEntityNotFoundException(
				 EntityNotFoundException entityNotFoundException) {
		 return entityNotFoundException.getMessage();
	}
		 @ExceptionHandler(IllegalArgumentException.class)
		 @ResponseStatus(HttpStatus.BAD_REQUEST)
		 public String handleEntityBadRequest(
				 IllegalArgumentException entityBadRequest) {
		 return entityBadRequest.getMessage();
		 }
		 
		 @ExceptionHandler(ConflictException.class)
		 @ResponseStatus(HttpStatus.CONFLICT)
		 public String conflicterror (
				 ConflictException conflictException) {
		 return conflictException.getMessage();
		 }
		 
	}
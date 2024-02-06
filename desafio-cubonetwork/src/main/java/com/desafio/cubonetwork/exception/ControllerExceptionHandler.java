package com.desafio.cubonetwork.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ExceptionDto> threatThrowable(Throwable throwable) {
		return ResponseEntity.badRequest().body(
				new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error"));
	}

	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<ExceptionDto> threatInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
		return ResponseEntity.badRequest().body(
				new ExceptionDto(HttpStatus.BAD_REQUEST.value(), "Missing fields"));
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ExceptionDto> threatNullPointerException(NullPointerException e) {
		return ResponseEntity.badRequest().body(
				new ExceptionDto(HttpStatus.BAD_REQUEST.value(), "Missing fields"));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionDto> threatHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		return ResponseEntity.badRequest().body(
				new ExceptionDto(HttpStatus.BAD_REQUEST.value(), "Invalid fields"));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionDto> threatBusinessException(BusinessException businessException) {
		return ResponseEntity.badRequest().body(
				new ExceptionDto(HttpStatus.BAD_REQUEST.value(), businessException.getMessage()));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ExceptionDto> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.badRequest().body(
				new ExceptionDto(HttpStatus.BAD_REQUEST.value(), "Invalid email or password"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ExceptionDto> handleValidationException(ValidationException e) {
		return ResponseEntity.badRequest().body(
				new ExceptionDto(HttpStatus.BAD_REQUEST.value(), "Invalid fields"));

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body(
				new ExceptionDto(HttpStatus.BAD_REQUEST.value(), "Invalid fields"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionDto> handleConstraintViolationException(ConstraintViolationException e) {
		return ResponseEntity.badRequest().body(new ExceptionDto(HttpStatus.BAD_REQUEST.value(), "Invalid fields"));
	}
}

package com.todo.todolist.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.todo.todolist.exceptions.ResourceAlreadyExistedException;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler({ ResourceAlreadyExistedException.class })
	public ResponseEntity<?> handleResouceAlreadyExistedException(Exception ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}
}

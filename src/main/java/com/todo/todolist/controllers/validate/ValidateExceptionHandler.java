package com.todo.todolist.controllers.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidateExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidate(MethodArgumentNotValidException ex){
		List<String> errors = new ArrayList<>();
		ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
		Map<String, List<String>> result = new HashMap<>();
		result.put("errors", errors);
		return ResponseEntity.badRequest().body(result);
	}

}

package com.todo.todolist.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
		@Email(message = "Invalid email") 
		@NotBlank(message = "Email is required")
		String email,

		@NotBlank(message = "Name must be not blank")
		String firstName,
		
		@NotBlank(message = "Name must be not blank")
		String lastName,

		@NotBlank(message = "Password is required")
		String password) {
}

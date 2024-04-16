package com.todo.todolist.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "`user`")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(unique = true, nullable = false, length = 320)
	private String email;

	@Column(nullable = false, length = 256)
	private String password;

	@Column(name = "first_name", nullable = false, length = 64)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 64)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 10, nullable = false)
	@Builder.Default
	private Role role = Role.USER;

	@Column(nullable = false, length = 64)
	private String avatar;

}

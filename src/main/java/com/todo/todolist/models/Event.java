package com.todo.todolist.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Event {

	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Group group;
	
	@Enumerated(EnumType.STRING)
	private EventType type;
	
	@ManyToOne
	private User mainActor;
	
	@ManyToOne
	private User secondaryActor1;
	
	@ManyToOne
	private Task secondaryActor2;
	
	@ManyToOne
	private User tertiaryActor;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	
}

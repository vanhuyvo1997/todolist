package com.todo.todolist.dtos.user;

import lombok.Builder;

@Builder
public record UserDto(String id, String firstName, String lastName, String email) {

}

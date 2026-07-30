package com.shubham.todoapi.mapper;

import com.shubham.todoapi.dto.response.TodoResponse;
import com.shubham.todoapi.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoResponse mapToResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted(),
                todo.getUser().getName()
        );
    }
}

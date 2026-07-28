package com.shubham.todoapi.service;

import com.shubham.todoapi.dto.CreateTodoRequest;
import com.shubham.todoapi.dto.TodoResponse;
import com.shubham.todoapi.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();

    private TodoResponse mapToResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted()
        );
    }

    public TodoService() {
        todos.add(new Todo(1L, "Learn Spring Boot", false));
        todos.add(new Todo(2L, "Build Todo API", false));
    }

    public List<TodoResponse> getAllTodos(){
        List<TodoResponse> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(mapToResponse(todo));
        }
        return response;
    }

    public TodoResponse getTodo(Long id){
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {
                return mapToResponse(todo);
            }
        }
        return null;
    }

    public TodoResponse createTodo(CreateTodoRequest request) {
        Long id = (long) (todos.size() + 1);
        Todo todo = new Todo(id, request.getTitle(), false);
        todos.add(todo);
        return mapToResponse(todo);
    }
}

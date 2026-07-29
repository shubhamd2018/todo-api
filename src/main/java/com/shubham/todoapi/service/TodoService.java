package com.shubham.todoapi.service;

import com.shubham.todoapi.dto.request.CreateTodoRequest;
import com.shubham.todoapi.dto.request.UpdateTodoRequest;
import com.shubham.todoapi.dto.response.TodoResponse;
import com.shubham.todoapi.entity.Todo;
import com.shubham.todoapi.exception.TodoNotFoundException;
import com.shubham.todoapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private TodoResponse mapToResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted()
        );
    }

    public List<TodoResponse> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponse> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(mapToResponse(todo));
        }
        return response;
    }

    public TodoResponse getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
        return mapToResponse(todo);
    }

    public TodoResponse createTodo(CreateTodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setCompleted(false);
        Todo savedTodo = todoRepository.save(todo);
        return mapToResponse(savedTodo);
    }

    public TodoResponse updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new TodoNotFoundException(id));
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return mapToResponse(updatedTodo);
    }
}

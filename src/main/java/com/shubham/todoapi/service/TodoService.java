package com.shubham.todoapi.service;

import com.shubham.todoapi.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();

    public TodoService() {
        todos.add(new Todo(1L, "Learn Spring Boot", false));
        todos.add(new Todo(2L, "Build Todo API", false));
    }

    public List<Todo> getAllTodos() {
        return todos;
    }
}

package com.shubham.todoapi.controller;

import com.shubham.todoapi.model.Todo;
import com.shubham.todoapi.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoService.getAllTodos();
    }
}

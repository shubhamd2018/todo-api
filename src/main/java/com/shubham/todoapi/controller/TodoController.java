package com.shubham.todoapi.controller;

import com.shubham.todoapi.dto.request.CreateTodoRequest;
import com.shubham.todoapi.dto.request.UpdateTodoRequest;
import com.shubham.todoapi.dto.response.TodoResponse;
import com.shubham.todoapi.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoResponse> getTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public TodoResponse createTodo(@Valid @RequestBody CreateTodoRequest request){
        return todoService.createTodo(request);
    }

    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable Long id, @Valid @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }
}

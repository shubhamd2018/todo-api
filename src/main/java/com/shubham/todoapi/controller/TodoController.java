package com.shubham.todoapi.controller;

import com.shubham.todoapi.dto.CreateTodoRequest;
import com.shubham.todoapi.dto.TodoResponse;
import com.shubham.todoapi.service.TodoService;
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
    public TodoResponse createTodo(@RequestBody CreateTodoRequest request){
        return todoService.createTodo(request);
    }

    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }
}

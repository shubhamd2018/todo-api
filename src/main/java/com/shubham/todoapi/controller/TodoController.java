package com.shubham.todoapi.controller;

import com.shubham.todoapi.dto.request.CreateTodoRequest;
import com.shubham.todoapi.dto.request.UpdateTodoRequest;
import com.shubham.todoapi.dto.response.TodoResponse;
import com.shubham.todoapi.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    //@GetMapping
    //public List<TodoResponse> getTodos(){
//        return todoService.getAllTodos();
//    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", params = "completed")
    public List<TodoResponse> getTodosByCompleted(@RequestParam boolean completed) {
        return todoService.getTodosByCompleted(completed);
    }

    @GetMapping(value = "/search", params = "title")
    public List<TodoResponse> getTodosByTitle(@RequestParam String title) {
        return todoService.getTodosByTitle(title);
    }

    @GetMapping("/search/title")
    public List<TodoResponse> searchTodosByTitle(@RequestParam String keyword) {
        return todoService.searchTodosByTitle(keyword);
    }

    @GetMapping("/page")
    public Page<TodoResponse> getTodos(@RequestParam int page, @RequestParam int size, @RequestParam(defaultValue = "id") String sortBy) {
        return todoService.getTodos(page, size, sortBy);
    }

    @PatchMapping("/{id}/complete")
    public TodoResponse completeTodo(@PathVariable Long id) {
        return todoService.completeTodo(id);
    }

    @GetMapping
    public List<TodoResponse> getTodosDynamically(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String title) {
        return todoService.getTodosDynamically(completed, userId, title);
    }

}

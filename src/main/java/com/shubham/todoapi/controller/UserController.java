package com.shubham.todoapi.controller;

import com.shubham.todoapi.dto.request.CreateUserRequest;
import com.shubham.todoapi.dto.response.TodoResponse;
import com.shubham.todoapi.dto.response.UserResponse;
import com.shubham.todoapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/{id}/todos")
    public List<TodoResponse> getUserTodos(@PathVariable Long id) {
        return userService.getUserTodos(id);
    }
}

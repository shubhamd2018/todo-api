package com.shubham.todoapi.service;

import com.shubham.todoapi.dto.request.CreateUserRequest;
import com.shubham.todoapi.dto.response.UserResponse;
import com.shubham.todoapi.entity.User;
import com.shubham.todoapi.exception.UserNotFoundException;
import com.shubham.todoapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail()
        );
        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        for (User user : users) {
            response.add(mapToResponse(user));
        }
        return response;
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapToResponse(user);
    }
}

package com.shubham.todoapi.service;

import com.shubham.todoapi.dto.request.CreateTodoRequest;
import com.shubham.todoapi.dto.request.UpdateTodoRequest;
import com.shubham.todoapi.dto.response.TodoResponse;
import com.shubham.todoapi.entity.Todo;
import com.shubham.todoapi.exception.TodoNotFoundException;
import com.shubham.todoapi.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public TodoResponse updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new TodoNotFoundException(id));
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());
        //Todo updatedTodo = todoRepository.save(todo);
        return mapToResponse(todoRepository.save(todo));
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteById(id);
    }

    public List<TodoResponse> getTodosByCompleted(boolean completed) {
        List<Todo> todos = todoRepository.findByCompleted(completed);
        List<TodoResponse> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(mapToResponse(todo));
        }
        return response;
    }

    public List<TodoResponse> getTodosByTitle(String title) {
        List<Todo> todos = todoRepository.findByTitleContainingIgnoreCase(title);
        List<TodoResponse> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(mapToResponse(todo));
        }
        return response;
    }

    public List<TodoResponse> searchTodosByTitle(String keyword) {
        List<Todo> todos = todoRepository.searchByTitle(keyword);
        List<TodoResponse> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(mapToResponse(todo));
        }
        return response;
    }

    public Page<TodoResponse> getTodos(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Todo> todoPage = todoRepository.findAll(pageable);
        return todoPage.map(this::mapToResponse);
    }
}

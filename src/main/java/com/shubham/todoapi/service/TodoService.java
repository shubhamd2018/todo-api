package com.shubham.todoapi.service;

import com.shubham.todoapi.dto.request.CreateTodoRequest;
import com.shubham.todoapi.dto.request.UpdateTodoRequest;
import com.shubham.todoapi.dto.response.TodoResponse;
import com.shubham.todoapi.entity.Todo;
import com.shubham.todoapi.entity.User;
import com.shubham.todoapi.exception.TodoNotFoundException;
import com.shubham.todoapi.exception.UserNotFoundException;
import com.shubham.todoapi.mapper.TodoMapper;
import com.shubham.todoapi.repository.TodoRepository;
import com.shubham.todoapi.repository.UserRepository;
import com.shubham.todoapi.specification.TodoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoResponse> getAllTodos() {
        List<Todo> todos = todoRepository.findAllWithUser();
        List<TodoResponse> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(todoMapper.mapToResponse(todo));
        }
        return response;
    }

    public TodoResponse getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
        return todoMapper.mapToResponse(todo);
    }

    public TodoResponse createTodo(CreateTodoRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException(request.getUserId()));

        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setCompleted(false);
        todo.setUser(user);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.mapToResponse(savedTodo);
    }

    @Transactional
    public TodoResponse updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new TodoNotFoundException(id));
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());
        //Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.mapToResponse(todo);
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
            response.add(todoMapper.mapToResponse(todo));
        }
        return response;
    }

    public List<TodoResponse> getTodosByTitle(String title) {
        List<Todo> todos = todoRepository.findByTitleContainingIgnoreCase(title);
        List<TodoResponse> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(todoMapper.mapToResponse(todo));
        }
        return response;
    }

    public List<TodoResponse> searchTodosByTitle(String keyword) {
        List<Todo> todos = todoRepository.searchByTitle(keyword);
        List<TodoResponse> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(todoMapper.mapToResponse(todo));
        }
        return response;
    }

    public Page<TodoResponse> getTodos(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Todo> todoPage = todoRepository.findAll(pageable);
        return todoPage.map(todo -> todoMapper.mapToResponse(todo));
    }

    @Transactional
    public TodoResponse completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() ->
                        new TodoNotFoundException(id));

        todo.setCompleted(true);
        return todoMapper.mapToResponse(todo);
    }

    public List<TodoResponse> getTodosDynamically(Boolean completed, Long userId, String title){
        Specification<Todo> specification = Specification.unrestricted();

        if (completed != null) {
            specification = specification.and(
                    TodoSpecification.hasCompleted(completed)
            );
        }
        if (userId != null) {
            specification = specification.and(
                    TodoSpecification.hasUserId(userId)
            );
        }
        if (title != null) {
            specification = specification.and(
                    TodoSpecification.hasTitleContaining(title)
            );
        }

        List<Todo> todos = todoRepository.findAll(specification);

        return todos.stream()
                .map(todoMapper::mapToResponse)
                .toList();
    }

}

package com.shubham.todoapi.repository;

import com.shubham.todoapi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCompleted(boolean completed);
    //List<Todo> findByTitle(String title);
    List<Todo> findByTitleContainingIgnoreCase(String title);
}


package com.shubham.todoapi.repository;

import com.shubham.todoapi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {
    List<Todo> findByCompleted(boolean completed);
    //List<Todo> findByTitle(String title);
    List<Todo> findByTitleContainingIgnoreCase(String title);

    @Query("""
       SELECT t
       FROM Todo t
       WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
       """)
    List<Todo> searchByTitle(@Param("keyword") String keyword);

    @Query("""
    SELECT t
    FROM Todo t
    JOIN FETCH t.user
    """)
    List<Todo> findAllWithUser();
}


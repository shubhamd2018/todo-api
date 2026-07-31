package com.shubham.todoapi.specification;

import com.shubham.todoapi.entity.Todo;
import org.springframework.data.jpa.domain.Specification;

public class TodoSpecification {

    public static Specification<Todo> hasCompleted(Boolean completed) {
        return (root, query, builder) ->
                builder.equal(root.get("completed"), completed);
    }

    public static Specification<Todo> hasUserId(Long userId) {
        return (root, query, builder) ->
                builder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Todo> hasTitleContaining(String title) {
        return (root, query, builder) ->
                builder.like(
                        builder.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                );
    }
}

package com.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.todo.domain.Todo;

public interface ITodoRepository extends CrudRepository<Todo, Long> {

}

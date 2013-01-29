package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.todo.domain.Todo;
import com.todo.repository.ITodoRepository;

@Controller
@RequestMapping("/todo")
public class TodoController {

  private final ITodoRepository todoRepo;

  @Autowired
  public TodoController(ITodoRepository todoRepo) {
    this.todoRepo = todoRepo;
  }

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Iterable<Todo> getAll() {
    return todoRepo.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Todo getTodo(@PathVariable("id") Long id) {
    return todoRepo.findOne(id);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public Long createTodo(@RequestBody Todo todo) {
    Todo created = todoRepo.save(todo);
    return created.getId();
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public void updateTodo(@RequestBody Todo todo) {
    todoRepo.save(todo);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void deleteTodo(@PathVariable("id") Long id) {
    todoRepo.delete(id);
  }

}

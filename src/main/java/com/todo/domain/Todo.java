package com.todo.domain;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.map.ObjectMapper;

@Entity
public class Todo {

  @Id
  @GeneratedValue
  private Long id;
  private String description;
  private boolean isDone;

  public Todo() {

  }

  public Todo(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  public String toJson() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

}

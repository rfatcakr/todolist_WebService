package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todolist.model.ToDoItem;
@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

}

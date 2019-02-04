package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todolist.model.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
	
}

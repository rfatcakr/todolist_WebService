package com.example.todolist.controller;

import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.model.ToDoList;
import com.example.todolist.repository.ToDoListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class ToDoListController {
	@Autowired
    ToDoListRepository todoRepository;
	
	
	@GetMapping("/todolist")
    public List<ToDoList> getAllNotes() {
        return todoRepository.findAll();
    }
	
    @PostMapping("/todolist")
    public ToDoList createToDoList(@Valid @RequestBody ToDoList toDoList) {
        return todoRepository.save(toDoList);
    }
    
    @GetMapping("/todolist/list/{id}")
    public ToDoList getTodoListById(@PathVariable(value = "id") Long id) {
    	List<ToDoList> list=new ArrayList<ToDoList>();
        for(ToDoList todolist:todoRepository.findAll()){
        	if(todolist.getId()==id) {
        		return todolist;
        	}
        }
        return null;
    }
	
    @GetMapping("/todolist/user/{user_id}")
    public List<ToDoList> getTodoListByUserId(@PathVariable(value = "user_id") Long user_id) {
    	List<ToDoList> list=new ArrayList<ToDoList>();
        for(ToDoList todolist:todoRepository.findAll()){
        	if(todolist.getUser_id()==user_id) {
        		list.add(todolist);
        	}
        }
        return list;
    }
    
    @PutMapping("/todolist/list/{id}")
    public ToDoList updateToDoListById(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody ToDoList userDetails) {

    	ToDoList todolist = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

    	todolist.setName(userDetails.getName());
    	ToDoList updatedNote = todoRepository.save(todolist);
        return updatedNote;
    }
    
    @PutMapping("/todolist/user/{user_id}")
    public ToDoList updateToDoListByUserId(@PathVariable(value = "user_id") Long user_id,
                                           @Valid @RequestBody ToDoList userDetails) {
    	for(ToDoList todolist:todoRepository.findAll()){
    		if(todolist.getUser_id()==user_id) {
    			todolist.setName(userDetails.getName());
    	    	ToDoList updatedNote = todoRepository.save(todolist);
    	    	return updatedNote;
    		}
    	}
    	
    
        return null;
    }
    
    
    @DeleteMapping("/todolist/list/{id}")
    public ResponseEntity<?> deleteUserByListId(@PathVariable(value = "id") Long Id) {
    	ToDoList todolist = todoRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", Id));

        todoRepository.delete(todolist);

        return ResponseEntity.ok().build();
    }
    

    
}

package com.example.todolist.controller;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.model.ToDoItem;
import com.example.todolist.repository.ToDoItemRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class ToDoItemController {
	@Autowired
	ToDoItemRepository todoItemRepository;
	
    @GetMapping("/todoitem")
    public List<ToDoItem> getAllItem() {
        return todoItemRepository.findAll();
    }

    @PostMapping("/todoitem")
    public ToDoItem createItem(@Valid @RequestBody ToDoItem todoItem) {
    	if(todoItem.getConnected()==null)
            return todoItemRepository.save(todoItem);

    	if(todoItem.getConnected()!=null && todoItem.getId()!=todoItem.getConnected()) {
    		boolean valid=false;
    		for(ToDoItem item:todoItemRepository.findAll()){
    			if(item.getId()==todoItem.getConnected())
    			{	
    				valid=true;
    				break;
    			}
    		}
    		if(valid)
    			return todoItemRepository.save(todoItem);
    	}
    	return null;
    }

    //take whole items connected to a list
    @GetMapping("/todoitem/{todolist_id}")
    public List<ToDoItem> getItemByListId(@PathVariable(value = "todolist_id") Long todolist_id) {
    	List<ToDoItem> Itemlist=new ArrayList<ToDoItem>();
    	for(ToDoItem item:todoItemRepository.findAll()){
        	if(item.getTodolist_id()==(todolist_id)) {
        		Itemlist.add(item);
        	}
        }
        return Itemlist;
    }
    //
    
    //delete based on the ID
    @DeleteMapping("/todoitem/item/{id}")
    public ResponseEntity<?> deleteUserByItemId(@PathVariable(value = "id") Long Id) {
    	ToDoItem todolist = todoItemRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", Id));
    	
    	todoItemRepository.delete(todolist);
     	
    	

        return ResponseEntity.ok().build();
    }
    
    //delete based on the ListID
    @DeleteMapping("/todoitem/list/{todolist_id}")
    public ResponseEntity<?> deleteUserByListId(@PathVariable(value = "todolist_id") Long Id) {

		for(ToDoItem item:todoItemRepository.findAll()){
		    		if(item.getTodolist_id()==Id)
		    	    	todoItemRepository.delete(item);
    	}


        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/todoitem/{id}")
    public ToDoItem updateToDoListById(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody ToDoItem details) {

    	ToDoItem todolist = todoItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

       	boolean valid=true;
    	if(details.getStatus()) {
    		//find the connected Item
    		for(ToDoItem item:todoItemRepository.findAll()){
    			if(item.getId()==details.getConnected())
    			{
    				//is this completed?
    				if(!item.getStatus())
    					valid=false;
    					
    			}
    		}
    	}
       	//Note that completed item cannot connect a new one
    	//check for right connection
    	if(!details.getStatus() && details.getConnected()!=null && details.getId()!=details.getConnected()) {
    		valid=false;
    		for(ToDoItem item:todoItemRepository.findAll()){
    			if(item.getId()==details.getConnected())
    			{	
    				valid=true;
    				break;
    			}
    		}
    	}
    	if(valid) {
			//lets add partial update ability. just a joke :)
	    	if(details.getTodolist_id()!=null)
	    		todolist.setTodolist_id(details.getTodolist_id());
	    	if(details.getName()!=null)
	    		todolist.setName(details.getName());
	    	if(details.getDescription()!=null)
	    		todolist.setDescription(details.getDescription());
	    	if(details.getStatus()!=null)
	    		todolist.setStatus((details.getStatus())?true:false);
	    	if(details.getCreatedDate()!=null)
	    		todolist.setCreatedDate(details.getCreatedDate());
	    	if(details.getDeadline()!=null)
	    		todolist.setDeadline(details.getDeadline());
	    	if(details.getConnected()!=null)
	    		todolist.setConnected(details.getConnected());
	    	
	    	ToDoItem updatedNote = todoItemRepository.save(todolist);
	        return updatedNote;
	
    
		}

    	return null;
    }
    
    
    
    
}

package com.example.todolist.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ToDoItem")
public class ToDoItem {
	private Long id;
	private Long todolist_id;
	private Long connected;
	private String name;
	private String description;
	private Boolean status=false;
	private String createdDate;
	private String deadline;
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTodolist_id() {
		return todolist_id;
	}
	public void setTodolist_id(Long todolist_id) {
		this.todolist_id = todolist_id;
	}
	
	public Long getConnected() {
		return connected;
	}
	public void setConnected(Long connected) {
		this.connected = connected;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	
}

package com.example.todolist.model;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TodoList")
public class ToDoList {
	private Long id;
	private String name;
	private Long user_id;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}





	
}

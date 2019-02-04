package com.example.todolist.controller;



import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.model.User;
import com.example.todolist.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getAllNotes() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User createNote(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

//    @GetMapping("/user/{id}")
//    public User getUserById(@PathVariable(value = "id") Long userId) {
//        return userRepository.findById(userId)
//        		.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//    }
    
    @GetMapping("/user/{userName}")
    public User getUserById(@PathVariable(value = "userName") String userName) {
        for(User user:userRepository.findAll()){
        	if(user.getUsername().equals(userName)) {
        		return user;
        	}
        }
        return null;
    }
    
    @PutMapping("/user/{id}")
    public User updateNote(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        User updatedNote = userRepository.save(user);
        return updatedNote;
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}

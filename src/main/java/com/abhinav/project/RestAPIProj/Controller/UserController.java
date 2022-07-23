package com.abhinav.project.RestAPIProj.Controller;

import com.abhinav.project.RestAPIProj.Entity.User;
import com.abhinav.project.RestAPIProj.Exception.ResourceNotFoundException;
import com.abhinav.project.RestAPIProj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//UserController class deals with various user operations (View, Add, Modify, Delete)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //Function to return a list of all users present in the Database
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    //Function to return a specific user in Database based on UserId
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));
    }

    //Function to create a user and add to the Database
    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return this.userRepository.save(user);
    }

    //Function to update user data by retrieving it based on UserId
    @PutMapping("/{id}")
    public User updateUser(@Valid @RequestBody User user, @PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        this.userRepository.save(existingUser);
        return this.userRepository.save(existingUser);
    }

    //Function to delete user data from Database based on UserId
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));
        this.userRepository.delete(existingUser);
        return "User Deleted Successfully with id "+userId;
    }

    /*public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }*/
}

package com.abhinav.project.RestAPIProj.Controller;

import com.abhinav.project.RestAPIProj.Entity.User;
import com.abhinav.project.RestAPIProj.Exception.ResourceNotFoundException;
import com.abhinav.project.RestAPIProj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return this.userRepository.save(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@Valid @RequestBody User user, @PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        this.userRepository.save(existingUser);
        return this.userRepository.save(existingUser);
    }

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

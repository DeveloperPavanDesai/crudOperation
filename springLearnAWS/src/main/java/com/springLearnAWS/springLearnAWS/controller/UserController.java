package com.springLearnAWS.springLearnAWS.controller;

import com.springLearnAWS.springLearnAWS.entity.User;
import com.springLearnAWS.springLearnAWS.exception.ResourceNotFoundException;
import com.springLearnAWS.springLearnAWS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //get all user
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId){
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"+userId));
    }

    //create user
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    //update user
    @PostMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId){
        User existUser =  this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"+userId));
        existUser.setFirstName(user.getFirstName());
        existUser.setLastName(user.getLastName());
        existUser.setEmail(user.getEmail());
        return this.userRepository.save(existUser);
    }

    //delete user by  id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
        User existUser =  this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"+userId));
        this.userRepository.delete(existUser);
        return ResponseEntity.ok().build();
    }
}

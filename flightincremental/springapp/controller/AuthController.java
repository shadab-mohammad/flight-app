package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserToken;
import com.examly.springapp.service.UserService;

@CrossOrigin(origins= "https://8081-cbfadfbbdaa322263798edccecfbdeone.premiumproject.examly.io")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;    
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) throws EntityNotFoundException{
        user = userService.createUser(user);
        return ResponseEntity.status(201).body(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        UserToken token = userService.loginUser(user);
        // if(token==null){
        //     return 
        // }
        return ResponseEntity.status(201).body(token);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) throws EntityNotFoundException{
        User user = userService.getUserById(userId);
        return ResponseEntity.status(200).body(user);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }
    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) throws EntityNotFoundException{
        User user = userService.getUserByUsername(username);
        return ResponseEntity.status(200).body(user);
    }
}

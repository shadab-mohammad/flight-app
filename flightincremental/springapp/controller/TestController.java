package com.examly.springapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Flight;

@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Flight Booking Application";
    }
    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> list = new ArrayList<>();
        return ResponseEntity.status(200).body(list);
    }
}

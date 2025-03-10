package com.examly.springapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.exception.InvalidDateException;
import com.examly.springapp.exception.SeatsExceededException;
import com.examly.springapp.model.Booking;
import com.examly.springapp.service.BookingService;

@CrossOrigin
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) throws SeatsExceededException, EntityNotFoundException, InvalidDateException{
        booking = bookingService.createBooking(booking);
        return ResponseEntity.status(201).body(booking);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable long id) throws EntityNotFoundException{
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.status(200).body(booking);
    }
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(){
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.status(200).body(bookings);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable long id, @RequestBody Booking booking) throws EntityNotFoundException{
        booking = bookingService.updateBooking(id,booking);
        return ResponseEntity.status(201).body(booking);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable int userId){
        List<Booking> bookings = bookingService.getAllBookingsByUserId(userId);
        return ResponseEntity.status(200).body(bookings);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBookingStatus(@PathVariable long id,@RequestBody Map<String,Boolean> status) throws EntityNotFoundException{
        bookingService.updateBookingStatus(id,status.get("status"));
        return ResponseEntity.ok().build();
    }
}



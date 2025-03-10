package com.examly.springapp.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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

import com.examly.springapp.exception.DuplicateFlightException;
import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.model.Flight;
import com.examly.springapp.service.FlightService;

@CrossOrigin
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) throws DuplicateFlightException{
        flight = flightService.addFlight(flight);
        return ResponseEntity.status(200).body(flight);
    }
    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable long flightId, @RequestBody Flight newFlight) throws EntityNotFoundException{
        newFlight = flightService.updateFlight(flightId, newFlight);
        return ResponseEntity.status(200).body(newFlight);
    }
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.status(200).body(flights);
    }
    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long flightId) throws EntityNotFoundException{
        Flight flight = flightService.getFlightById(flightId);
        return ResponseEntity.status(200).body(flight);
    }
    @GetMapping("/{source}/{destination}")
    public ResponseEntity<List<Flight>> getFlightsBySourceAndDestination(@PathVariable String source, @PathVariable String destination) throws EntityNotFoundException{
        List<Flight> flights = flightService.getFlightsBySourceAndDestination(source,destination);
        return ResponseEntity.status(200).body(flights);
    }
    @DeleteMapping("/{flightId}")
    public ResponseEntity<Boolean> deleteFlightById(@PathVariable long flightId) throws EntityNotFoundException{
        
        return ResponseEntity.status(200).body(flightService.deleteFlightById(flightId));
    }

}

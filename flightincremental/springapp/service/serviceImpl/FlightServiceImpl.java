package com.examly.springapp.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicateFlightException;
import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.model.Flight;
import com.examly.springapp.repository.FlightRepo;
import com.examly.springapp.service.FlightService;

/**
 * This is a FlightService class that performs basic operations on Flight entity.
 * 
 * @author mohammadShadab
 */

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    private FlightRepo flightRepo;
    
    /**
     * Adds a flight entity
     * @throws DuplicateFlightException 
     */
    @Override
    public Flight addFlight(Flight flight) throws DuplicateFlightException{

        //flightalreadyexistsexception
        Flight existingFlight = flightRepo.findByFlightNumber(flight.getFlightNumber());
        if(existingFlight != null){
            throw new DuplicateFlightException("Flight number "+flight.getFlightNumber()+" already exists");
        }
        flight = flightRepo.save(flight);
        return flight;
        
    }

    /**
     * Updates a flight entity
     */
    @Override
    public Flight updateFlight(long flightId, Flight newFlight) throws EntityNotFoundException{
        Flight oldFlight = flightRepo.findById(flightId).orElse(null);
        if(oldFlight==null){
            throw new EntityNotFoundException("Flight with ID: "+flightId+" not found");
        }
        newFlight.setFlightId(flightId);
        newFlight = flightRepo.save(newFlight);
        return newFlight;
    }

    /**
     * Retrieves a list of all flights
     */
    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    /**
     * Retrieves a flight by its ID
     */
    public Flight getFlightById(long flightId) throws EntityNotFoundException {
        Flight flight = flightRepo.findById(flightId).orElse(null);
        if(flight==null){
            throw new EntityNotFoundException("Flight with ID "+flightId+" not found");
        }
        return flight;
    }

    /**
     * Deletes a flight by its ID
     */
    public boolean deleteFlightById(long flightId) throws EntityNotFoundException {
        Flight flight = flightRepo.findById(flightId).orElse(null);
        if(flight==null){
            throw new EntityNotFoundException("Flight with ID "+flightId+" not found");
        }
        flightRepo.deleteById(flightId);
        return true;
    }

    /**
     * Retrieves a list of flights by source and Destination
     */
    @Override
    public List<Flight> getFlightsBySourceAndDestination(String source, String destination) throws EntityNotFoundException {
        List<Flight> flights = flightRepo.getFlightsBySourceAndDestination(source,destination);
        if(flights.isEmpty()){
            throw new EntityNotFoundException("Flight not found");
        }
        return flights;
    }
}

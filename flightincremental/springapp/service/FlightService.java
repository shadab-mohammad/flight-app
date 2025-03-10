package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.exception.DuplicateFlightException;
import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.model.Flight;

public interface FlightService {
    Flight addFlight(Flight flight) throws DuplicateFlightException;
    Flight updateFlight(long flightId, Flight newFlight) throws EntityNotFoundException;
    List<Flight> getAllFlights();
    Flight getFlightById(long flightId) throws EntityNotFoundException;
    boolean deleteFlightById(long flightId) throws EntityNotFoundException;
    List<Flight> getFlightsBySourceAndDestination(String source, String destination) throws EntityNotFoundException;
}

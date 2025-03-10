package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight,Long>{
    //flightnumber
    @Query("select f from Flight f where f.departureLocation= ?1 and f.arrivalLocation =?2")
    List<Flight> getFlightsBySourceAndDestination(String source, String destination);

    Flight findByFlightNumber(String flightNumber);

}

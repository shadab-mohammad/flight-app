package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.exception.InvalidDateException;
import com.examly.springapp.exception.SeatsExceededException;
import com.examly.springapp.model.Booking;
import com.examly.springapp.model.Flight;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.BookingRepo;

/**
 * This a BookingService class that performs basic operations on Booking
 * 
 * @author mohammadShadab
 */

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private FlightService flightService;
    @Autowired
    private UserService userService;
    /**
     * Adds a booking object to the entity
     * @throws InvalidDateException 
     */
    @Override
    public Booking createBooking(Booking booking) throws SeatsExceededException, EntityNotFoundException, InvalidDateException {
        if(booking.getBookingDate().isBefore(LocalDate.now())){
            throw new InvalidDateException("Invalid date of booking");
        }
        
        Flight flight = flightService.getFlightById(booking.getFlight().getFlightId());
        User user = userService.getUserById(booking.getUser().getUserId());
        int totalBookedSeats = bookingRepo.countTotalBookedSeats(booking.getBookingDate(),booking.getFlight().getFlightId());
        int totalAvailableSeats = flight.getTotalSeats()-totalBookedSeats;

        if(booking.getNumberOfPassengers() > totalAvailableSeats){
            throw new SeatsExceededException("No enough seats");
        }
        double totalCost = flight.getPrice()*booking.getNumberOfPassengers();
        booking.setTotalCost(totalCost);
        booking.setStatus(false);
        booking.setFlight(flight);
        booking.setUser(user);
        return bookingRepo.save(booking);
    }

    /**
     * Retrieves a booking by its ID
     */
    @Override
    public Booking getBookingById(Long id) throws EntityNotFoundException {
        Booking booking = bookingRepo.findById(id).orElse(null);
        if (booking == null) {
            throw new EntityNotFoundException("No Booking found with the given ID");
        }
        return booking;
    }

    /**
     * Retrieves a list of bookings
     */
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    /**
     * Updates a booking by its ID
     */
    @Override
    public Booking updateBooking(Long id, Booking booking) throws EntityNotFoundException {
        Booking existingBooking = bookingRepo.findById(id).orElse(null);
        if (existingBooking == null) {
            throw new EntityNotFoundException("No Booking found with the given ID");
        }
        booking.setBookingId(id);
        return bookingRepo.save(booking);
    }

    /**
     * Retrieves a list of bookings by userId
     */
    @Override
    public List<Booking> getAllBookingsByUserId(int userId) {
        List<Booking> bookings = bookingRepo.getAllBookingsByUserId(userId);
        return bookings;
    }

    @Override
    public Booking updateBookingStatus(long id, boolean status) throws EntityNotFoundException {
       Booking booking = bookingRepo.findById(id).orElse(null);
       if(booking==null){
        throw new EntityNotFoundException("No Booking found with the given ID");
       }
       booking.setStatus(status);
       return bookingRepo.save(booking);
    }
    

}

package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.exception.InvalidDateException;
import com.examly.springapp.exception.SeatsExceededException;
import com.examly.springapp.model.Booking;

public interface BookingService {
    Booking createBooking(Booking booking) throws SeatsExceededException,EntityNotFoundException, InvalidDateException;
    Booking getBookingById(Long id) throws EntityNotFoundException;
    List<Booking> getAllBookings();
    Booking updateBooking(Long id, Booking booking) throws EntityNotFoundException;
    List<Booking> getAllBookingsByUserId(int userId);
    Booking updateBookingStatus(long id, boolean status) throws EntityNotFoundException;
}

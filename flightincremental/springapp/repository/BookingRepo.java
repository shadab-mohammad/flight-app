package com.examly.springapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long>{
    @Query("select b from Booking b where b.user.userId=?1")
    List<Booking> getAllBookingsByUserId(int userId);

    @Query("select coalesce(sum(b.numberOfPassengers),0) from Booking b where b.bookingDate=?1 and b.flight.flightId=?2")
    int countTotalBookedSeats(LocalDate bookingDate, long flightId);
    
}

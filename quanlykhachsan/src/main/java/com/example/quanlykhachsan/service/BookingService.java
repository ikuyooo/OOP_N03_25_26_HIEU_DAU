package com.example.quanlykhachsan.service;

import com.example.quanlykhachsan.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
   
    Booking createBooking(Long customerId, Long roomId, LocalDate checkIn, LocalDate checkOut);

    
    List<Booking> getBookingsByCustomerId(Long customerId);
   
    List<Booking> getAllBookings();
    
    void cancelBooking(Long bookingId);
}
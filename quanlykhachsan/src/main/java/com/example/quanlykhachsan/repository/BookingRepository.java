package com.example.quanlykhachsan.repository;

import com.example.quanlykhachsan.model.Booking;
import com.example.quanlykhachsan.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


    List<Booking> findByCustomerId(Long customerId);


    List<Booking> findByRoomId(Long roomId);


    List<Booking> findByStatus(BookingStatus status);


    @Query("SELECT b FROM Booking b " +
           "WHERE b.room.id = :roomId " +
           "AND (b.checkInDate < :checkOut AND b.checkOutDate > :checkIn) " +
           "AND b.status != 'CANCELLED'")
    List<Booking> findConflictingBookings(
            @Param("roomId") Long roomId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);
}
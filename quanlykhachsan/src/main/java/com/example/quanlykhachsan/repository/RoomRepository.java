package com.example.quanlykhachsan.repository;

import com.example.quanlykhachsan.model.Room;
import com.example.quanlykhachsan.model.RoomStatus;
import com.example.quanlykhachsan.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    
    List<Room> findByStatus(RoomStatus status);

    
    List<Room> findByType(RoomType type);

    
    @Query("SELECT r FROM Room r WHERE r.id NOT IN (" +
           "SELECT b.room.id FROM Booking b " +
           "WHERE (b.checkInDate < :checkOut AND b.checkOutDate > :checkIn) " +
           "AND b.status != 'CANCELLED'" +
           ")")
    List<Room> findAvailableRooms(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);
 boolean existsByCode(String code);           
}
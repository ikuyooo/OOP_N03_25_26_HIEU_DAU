package com.example.quanlykhachsan.service;

import com.example.quanlykhachsan.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    
    List<Room> getAllRooms();

    
    Room getRoomById(Long roomId);

    
    Room createRoom(Room room);

   
    Room updateRoom(Long roomId, Room roomDetails);

   
    void deleteRoom(Long roomId);

    List<Room> findAvailableRooms(LocalDate checkIn, LocalDate checkOut);
}
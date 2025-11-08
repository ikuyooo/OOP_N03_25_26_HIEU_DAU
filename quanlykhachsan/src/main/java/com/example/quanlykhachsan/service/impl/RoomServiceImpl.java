package com.example.quanlykhachsan.service.impl;

import com.example.quanlykhachsan.exception.ResourceNotFoundException;
import com.example.quanlykhachsan.model.Room;
import com.example.quanlykhachsan.repository.RoomRepository;
import com.example.quanlykhachsan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
    }

    @Override
    public Room createRoom(Room room) {
        // (Admin) Có thể thêm logic validation 
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long roomId, Room roomDetails) {
        Room room = getRoomById(roomId); 
        
        // Cập nhật thông tin
        room.setCode(roomDetails.getCode());
        room.setType(roomDetails.getType());
        room.setStatus(roomDetails.getStatus());
        room.setPrice(roomDetails.getPrice());
        room.setDescription(roomDetails.getDescription());
        
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long roomId) {
        Room room = getRoomById(roomId);
        roomRepository.delete(room);
    }

    @Override
    public List<Room> findAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        
        return roomRepository.findAvailableRooms(checkIn, checkOut);
    }
}
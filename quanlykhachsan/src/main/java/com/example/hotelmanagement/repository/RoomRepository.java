package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // Có thể thêm custom query sau nếu cần
}

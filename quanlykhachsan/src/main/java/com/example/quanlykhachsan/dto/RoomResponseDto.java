package com.example.quanlykhachsan.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class RoomResponseDto {
    private int roomId;
    private String roomNumber;
    private String roomType;
    private BigDecimal pricePerNight;
    private int capacity;
    private String status;
    private String description;
    private Timestamp createdAt;

    // ===== Constructor =====
    public RoomResponseDto() {}

    public RoomResponseDto(int roomId, String roomNumber, String roomType, BigDecimal pricePerNight,
                           int capacity, String status, String description, Timestamp createdAt) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
    }

    // ===== Getter & Setter =====
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

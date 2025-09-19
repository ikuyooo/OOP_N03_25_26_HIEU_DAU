package com.example.hotelbooking.hotel_booking;
import java.io.Serializable;
import java.util.Objects;


public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean status;

     public Room() {
    }

    public Room(int roomNumber, String roomType, double price, boolean status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
    }

    // Getter & Setter
    public int getroomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getroomType() {
        return roomType;
    }
    public void setroomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
  
    
    @Override
    public String toString() {
        return "Room{" +
                "Số phòng: " + roomNumber +
                ",Loại phòng: " + roomType + '\'' +
                ",Giá phòng: " + price +
                ",Trạng thái: " + status + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
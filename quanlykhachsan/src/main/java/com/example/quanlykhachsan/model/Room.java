package com.example.quanlykhachsan.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; // Import cho BigDecimal

@Entity
@Table(name = "rooms")
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String type;
    private BigDecimal price; // *** ĐÃ ĐỔI TỪ Double SANG BigDecimal ***
    
    private int floor; // Thuộc tính 'floor' được giữ nguyên
    
    private String status;
    private String description; 

    // Constructor mặc định
    public Room() {}

    // Constructor đầy đủ
    public Room(String code, String type, BigDecimal price, int floor, String status, String description) {
        this.code = code;
        this.type = type;
        this.price = price;
        this.floor = floor;
        this.status = status;
        this.description = description;
    }

    // --- Getters and Setters ---
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Đã sửa kiểu trả về và tham số từ Double sang BigDecimal
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
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
}
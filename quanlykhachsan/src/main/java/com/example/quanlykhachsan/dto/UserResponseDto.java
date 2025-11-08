package com.example.quanlykhachsan.dto;

import java.sql.Timestamp;

public class UserResponseDto {
    private int userId;
    private String username;
    private String role;
    private boolean isActive;
    private Timestamp lastLogin;
    private Timestamp createdAt;

    public UserResponseDto() {}

    public UserResponseDto(int userId, String username, String role, boolean isActive,
                           Timestamp lastLogin, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.isActive = isActive;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
    }

    // Getter & Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

package com.example.quanlykhachsan.service;

import com.example.quanlykhachsan.dto.CustomerRegistrationDto;
import com.example.quanlykhachsan.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    
    void registerCustomer(CustomerRegistrationDto registrationDto);

    
    User findByUsername(String username);
}
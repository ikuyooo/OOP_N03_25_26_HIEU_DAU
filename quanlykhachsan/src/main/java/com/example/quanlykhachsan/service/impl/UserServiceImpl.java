package com.example.quanlykhachsan.service.impl;

import com.example.quanlykhachsan.dto.CustomerRegistrationDto;
import com.example.quanlykhachsan.exception.ResourceNotFoundException;
import com.example.quanlykhachsan.model.Customer;
import com.example.quanlykhachsan.model.User;
import com.example.quanlykhachsan.repository.CustomerRepository;
import com.example.quanlykhachsan.repository.UserRepository;
import com.example.quanlykhachsan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           CustomerRepository customerRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy User với username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPasswordHash(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }

   
    @Override
    @Transactional
    public void registerCustomer(CustomerRegistrationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Lỗi: Username '" + dto.getUsername() + "' đã được sử dụng!");
        }
        
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRole("CUSTOMER"); 
        user.setActive(true);
        User savedUser = userRepository.save(user);

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setIdCard(dto.getIdCard());
        customer.setAddress(dto.getAddress());
        
        customer.setUser(savedUser);
        
        customerRepository.save(customer); 
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }
}
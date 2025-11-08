package com.example.quanlykhachsan.repository;

import com.example.quanlykhachsan.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUserUserId(Integer userId);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}
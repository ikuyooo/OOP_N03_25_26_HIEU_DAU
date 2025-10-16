package com.example.quanlykhachsan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.quanlykhachsan.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

package com.example.quanlykhachsan.service;

import com.example.quanlykhachsan.model.Customer;

public interface CustomerService {
    
    Customer getCustomerByUserId(Integer userId);
    Customer getCustomerByEmail(String email);

    Customer updateCustomerProfile(Long customerId, Customer customerDetails);
}
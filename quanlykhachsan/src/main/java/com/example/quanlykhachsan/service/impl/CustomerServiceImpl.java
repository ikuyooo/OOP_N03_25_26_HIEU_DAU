package com.example.quanlykhachsan.service.impl;

import com.example.quanlykhachsan.exception.ResourceNotFoundException;
import com.example.quanlykhachsan.model.Customer;
import com.example.quanlykhachsan.repository.CustomerRepository;
import com.example.quanlykhachsan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService { 

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerByUserId(Integer userId) {
        return customerRepository.findByUserUserId(userId) 
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "userId", userId));
    }



@Override
public Customer getCustomerByEmail(String email) {
    return customerRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));
}

    @Override
    public Customer updateCustomerProfile(Long customerId, Customer customerDetails) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        customer.setName(customerDetails.getName());
        customer.setPhone(customerDetails.getPhone());
        customer.setEmail(customerDetails.getEmail());
        customer.setAddress(customerDetails.getAddress());
        customer.setIdCard(customerDetails.getIdCard());

        return customerRepository.save(customer);
    }
}
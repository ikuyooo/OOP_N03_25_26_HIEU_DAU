package com.example.quanlykhachsan.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "phone_number", nullable = false, length = 15, unique = true)
    private String phone;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 255)
    private String address;

    @Column(name = "id_card", length = 20, unique = true)
    private String idCard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Booking> bookings = new HashSet<>();



    //Constructor
    public Customer() {
    }
    
    public Customer(String name, String phone, String email, String address, LocalDate localDate, String idCard, String string, User user5) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.idCard = idCard;
    }

//Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

 //equals() và hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        // Sử dụng id cho hashCode và equals nếu đối tượng đã được persist
        // Nếu chưa persist, có thể dùng thuộc tính unique như phone
        return Objects.equals(id, customer.id); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
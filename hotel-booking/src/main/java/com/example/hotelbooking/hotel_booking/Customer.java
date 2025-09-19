package com.example.hotelbooking.hotel_booking;
import java.io.Serializable;
import java.util.Objects;


public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
      
    private int customerId;
    private String name;
    private String phoneNumber;
    private String email;
    private String CCCD;

    public Customer(){
        
    }
    public Customer(int customerId, String name, String phoneNumber, String email, String CCCD){
        this.customerId =customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.CCCD = CCCD;
    }
    //getter and setter 

    public int getcustomerId(){
        return customerId;
    }  
    public void setcustomerId(int customerId){
        this.customerId = customerId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getCCCD(){
        return CCCD;
    }
    public void setCCCD(String CCCD){
        this.CCCD = CCCD;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", CCCD='" + CCCD + '\'' +
                '}';

    }
      @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
}
}
package com.example.quanlykhachsan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDto {
    private Long bookingId;
    private Long roomId;
    private Long customerId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal total;

    public BookingDto(){}

    // getters/setters
    public Long getBookingId(){ return bookingId; }
    public void setBookingId(Long bookingId){ this.bookingId = bookingId; }

    public Long getRoomId(){ return roomId; }
    public void setRoomId(Long roomId){ this.roomId = roomId; }

    public Long getCustomerId(){ return customerId; }
    public void setCustomerId(Long customerId){ this.customerId = customerId; }

    public LocalDate getCheckIn(){ return checkIn; }
    public void setCheckIn(LocalDate checkIn){ this.checkIn = checkIn; }

    public LocalDate getCheckOut(){ return checkOut; }
    public void setCheckOut(LocalDate checkOut){ this.checkOut = checkOut; }

    public BigDecimal getTotal(){ return total; }
    public void setTotal(BigDecimal total){ this.total = total; }
}

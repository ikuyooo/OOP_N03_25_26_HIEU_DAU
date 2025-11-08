package com.example.quanlykhachsan.service.impl;

import com.example.quanlykhachsan.exception.ResourceNotFoundException;
import com.example.quanlykhachsan.model.Booking;
import com.example.quanlykhachsan.model.BookingStatus;
import com.example.quanlykhachsan.model.Customer;
import com.example.quanlykhachsan.model.Room;
import com.example.quanlykhachsan.repository.BookingRepository;
import com.example.quanlykhachsan.repository.CustomerRepository;
import com.example.quanlykhachsan.repository.RoomRepository;
import com.example.quanlykhachsan.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;


    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              CustomerRepository customerRepository,
                              RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Booking createBooking(Long customerId, Long roomId, LocalDate checkIn, LocalDate checkOut) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(roomId, checkIn, checkOut);
        if (!conflictingBookings.isEmpty()) {
            throw new RuntimeException("Phòng đã bị đặt trong khoảng thời gian này!");
        }

        if (!"VACANT".equals(room.getStatus())) {
            throw new RuntimeException("Phòng không ở trạng thái sẵn sàng (VACANT). Trạng thái hiện tại: " + room.getStatus());
        }
        
        long numberOfDays = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (numberOfDays <= 0) {
            throw new RuntimeException("Ngày trả phòng phải sau ngày nhận phòng.");
        }
        BigDecimal totalAmount = room.getPrice().multiply(new BigDecimal(numberOfDays));

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setRoom(room);
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setTotalAmount(totalAmount);
        
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByCustomerId(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer", "id", customerId);
        }
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));

        if (booking.getStatus() == BookingStatus.PENDING || booking.getStatus() == BookingStatus.CONFIRMED) {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Không thể hủy đơn đặt phòng ở trạng thái: " + booking.getStatus());
        }
    }
}
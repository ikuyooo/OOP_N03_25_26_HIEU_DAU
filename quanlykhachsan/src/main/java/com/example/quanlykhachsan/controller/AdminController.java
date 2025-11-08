package com.example.quanlykhachsan.controller;

import com.example.quanlykhachsan.model.Booking;
import com.example.quanlykhachsan.model.Customer;
import com.example.quanlykhachsan.model.Room;
import com.example.quanlykhachsan.service.BookingService;
import com.example.quanlykhachsan.service.CustomerService;
import com.example.quanlykhachsan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin") 
public class AdminController { 

    private final CustomerService customerService;
    private final BookingService bookingService;
    private final RoomService roomService;

    @Autowired
    public AdminController(CustomerService customerService,
                           BookingService bookingService,
                           RoomService roomService) {
        this.customerService = customerService;
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

     //Hiển thị form tìm kiếm khách hàng và kết quả
    
   
    @GetMapping("/customer-search")
    public String searchCustomerByEmail(
            @RequestParam(value = "email", required = false) String email,
            Model model
    ) {
        if (email != null && !email.isEmpty()) {
            try {
                Customer customer = customerService.getCustomerByEmail(email);
                List<Booking> bookings = bookingService.getBookingsByCustomerId(customer.getId());

                model.addAttribute("customer", customer);
                model.addAttribute("bookings", bookings);

            } catch (Exception e) {
                model.addAttribute("errorMessage", "Không tìm thấy khách hàng với email: " + email);
            }
        }
        return "admin/customer-search";
    }

    
     //(GET): Hiển thị form đặt phòng cho Lễ tân
     
    @GetMapping("/book-for-customer")
    public String showAdminBookingForm(@RequestParam("roomId") Long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return "admin/booking-form"; 
    }

    
     // (POST): Xử lý booking từ form Lễ tân
     
    @PostMapping("/book-for-customer")
    public String processAdminBooking(
            @RequestParam("roomId") Long roomId,
            @RequestParam("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @RequestParam("customerEmail") String customerEmail,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Customer customer = customerService.getCustomerByEmail(customerEmail);
            Long customerId = customer.getId();
            bookingService.createBooking(customerId, roomId, checkIn, checkOut);

            redirectAttributes.addFlashAttribute("successMessage",
                "Tạo booking thành công cho phòng " + roomService.getRoomById(roomId).getCode() +
                " (Khách hàng: " + customer.getName() + ")");
                
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                "Lỗi: " + e.getMessage());
            return "redirect:/admin/book-for-customer?roomId=" + roomId;
        }

        return "redirect:/rooms/dashboard";
    }

} 
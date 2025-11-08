package com.example.quanlykhachsan.controller;

import com.example.quanlykhachsan.model.Customer;
import com.example.quanlykhachsan.model.Room;
import com.example.quanlykhachsan.model.User;
import com.example.quanlykhachsan.service.BookingService;
import com.example.quanlykhachsan.service.CustomerService;
import com.example.quanlykhachsan.service.RoomService;
import com.example.quanlykhachsan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final CustomerService customerService;
    private final UserService userService;

    @Autowired
    public BookingController(BookingService bookingService, RoomService roomService,
                             CustomerService customerService, UserService userService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping("/admin/new")
    public String showAdminBookingForm(
            @RequestParam("roomId") Long roomId,
            Model model
    ) {
        Room room = roomService.getRoomById(roomId);
 
        if (room == null) {
             model.addAttribute("errorMessage", "Phòng không tồn tại.");

             return "redirect:/rooms/dashboard";
        }

        model.addAttribute("room", room);
        model.addAttribute("checkIn", LocalDate.now());
        model.addAttribute("checkOut", LocalDate.now().plusDays(1));
        model.addAttribute("customerEmail", ""); 
        
        return "admin/booking-form"; 
    }


    @PostMapping("/admin/reserve") 
    public String createAdminBooking(
            @RequestParam("roomId") Long roomId,
            @RequestParam("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @RequestParam("customerEmail") String customerEmail, 
            RedirectAttributes redirectAttributes
    ) {
        String redirectUrl = "redirect:/bookings/admin/new?roomId=" + roomId;
        
        try {
            User user = userService.findByUsername(customerEmail); 
        
            Customer customer = customerService.getCustomerByUserId(user.getUserId());
            
            if (customer == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Tài khoản User này chưa có hồ sơ khách hàng liên kết.");
                return redirectUrl;
            }

            bookingService.createBooking(customer.getId(), roomId, checkIn, checkOut);

            redirectAttributes.addFlashAttribute("successMessage", 
                "Đã tạo Booking thành công cho phòng " + roomService.getRoomById(roomId).getCode() + 
                " (Khách hàng: " + customerEmail + ")");
            return "redirect:/rooms/dashboard"; 
            
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi Booking: " + e.getMessage());
            return redirectUrl;
        }
    }


    @GetMapping("/confirm")
    public String showBookingConfirmationForm(
            @RequestParam("roomId") Long roomId,
            @RequestParam("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            Model model
    ) {
        Room room = roomService.getRoomById(roomId);
        if (room == null) {
            model.addAttribute("errorMessage", "Phòng không tồn tại.");
            return "redirect:/rooms/search";
        }
        
        model.addAttribute("room", room);
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);

        return "booking-confirm"; 
    }
    @PostMapping("/confirm") 
    public String createCustomerBooking(
            @RequestParam("roomId") Long roomId,
            @RequestParam("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            Principal principal, // Lấy thông tin người dùng đang đăng nhập
            RedirectAttributes redirectAttributes
    ) {
        String redirectUrl = String.format("redirect:/bookings/confirm?roomId=%d&checkIn=%s&checkOut=%s", 
                                          roomId, checkIn, checkOut);
        try {

            String username = principal.getName();
            User currentUser = userService.findByUsername(username);
            
            Customer customer = customerService.getCustomerByUserId(currentUser.getUserId());
            
            if (customer == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy hồ sơ khách hàng liên kết với tài khoản.");
                return "redirect:/home"; 
            }

            bookingService.createBooking(customer.getId(), roomId, checkIn, checkOut);

            redirectAttributes.addFlashAttribute("successMessage", "Đặt phòng thành công!");
            return "redirect:/bookings/history"; 

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return redirectUrl;
        }
    }

    @GetMapping("/history")
    public String showBookingHistory(Model model, Principal principal) {
        try {

            String username = principal.getName();
            User currentUser = userService.findByUsername(username);
            Customer customer = customerService.getCustomerByUserId(currentUser.getUserId());

            model.addAttribute("bookings", bookingService.getBookingsByCustomerId(customer.getId()));
            return "booking-history"; 

        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Không thể tải lịch sử đặt phòng: " + e.getMessage());
            return "home";
        }
    }
}
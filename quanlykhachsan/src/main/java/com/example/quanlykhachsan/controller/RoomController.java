package com.example.quanlykhachsan.controller;

import com.example.quanlykhachsan.model.Room;
import com.example.quanlykhachsan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/rooms") 
public class RoomController {

    private final RoomService roomService;


    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }



     //Hiển thị trang tìm kiếm phòng trống
   
    @GetMapping("/search")
    public String searchAvailableRooms(
            @RequestParam(value = "checkIn", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam(value = "checkOut", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            Model model
    ) {
        if (checkIn != null && checkOut != null) {
            // Lọc các phòng đã tìm thấy theo trạng thái trống trong khoảng thời gian
            List<Room> availableRooms = roomService.findAvailableRooms(checkIn, checkOut);
            model.addAttribute("rooms", availableRooms);
            model.addAttribute("checkIn", checkIn);
            model.addAttribute("checkOut", checkOut);
        }

        return "room-search"; 
    }

   

   //(Admin) Hiển thị danh sách tất cả phòng để quản lý
    
    @GetMapping("/admin")
    public String listAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "admin/room-list"; // View admin/room-list.html
    }
    
    
     // (Admin) Hiển thị Sơ đồ phòng trực quan (ĐÃ NÂNG CẤP VỚI FILTER)
    
    @GetMapping("/dashboard")
    public String showRoomDashboard(
           
            @RequestParam(value = "roomType", required = false, defaultValue = "TAT_CA") String roomType,
            @RequestParam(value = "roomStatus", required = false, defaultValue = "TAT_CA") String roomStatus,
            Model model
    ) {
        List<Room> allRooms = roomService.getAllRooms();

        // Lọc theo Loại phòng và Trạng thái 
        Stream<Room> filteredStream = allRooms.stream();
        if (!"TAT_CA".equals(roomType)) {
            filteredStream = filteredStream.filter(room -> roomType.equals(room.getType()));
        }
        if (!"TAT_CA".equals(roomStatus)) {
            filteredStream = filteredStream.filter(room -> roomStatus.equals(room.getStatus()));
        }
        List<Room> filteredRooms = filteredStream.collect(Collectors.toList());

        // Sắp xếp và Nhóm các phòng theo Tầng 
        filteredRooms.sort(Comparator.comparing(Room::getCode));
        Map<Integer, List<Room>> roomsByFloor = filteredRooms.stream()
                .collect(Collectors.groupingBy(
                    room -> Integer.parseInt(room.getCode().substring(0, 1)), 
                    TreeMap::new,
                    Collectors.toList()
                ));
        
        // Gửi dữ liệu về view
        model.addAttribute("floors", roomsByFloor);
        
        model.addAttribute("allRoomTypes", List.of("SGL", "DBL", "TWN", "TRPL", "VIP")); 
        model.addAttribute("allRoomStatuses", List.of("VACANT", "OCCUPIED", "CLEANING", "MAINTENANCE")); 
        model.addAttribute("selectedType", roomType); 
        model.addAttribute("selectedStatus", roomStatus); 

        return "admin/room-dashboard"; 
    }

  
     //(Admin) Hiển thị form tạo phòng mới
   
 
    @GetMapping("/new")
    public String showCreateRoomForm(Model model) {
        model.addAttribute("room", new Room());
       
        model.addAttribute("isNew", true); 
        
        return "admin/room-form"; 
    }

    // (Admin) Xử lý tạo phòng mới
    
   @PostMapping("/new")
    public String createRoom(@ModelAttribute Room room, RedirectAttributes redirectAttributes) {
        try {
         
            if (room.getStatus() == null || room.getStatus().isEmpty()) {
               
                room.setStatus("VACANT"); 
            }
            
            roomService.createRoom(room);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo phòng mới thành công!");
        } catch (Exception e) {
             redirectAttributes.addFlashAttribute("errorMessage", "Tạo phòng thất bại: " + e.getMessage());
        }
        return "redirect:/rooms/admin";
    }

    
     // (Admin) Hiển thị form chỉnh sửa phòng
    
    @GetMapping("/edit/{id}")
    public String showEditRoomForm(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
      
        model.addAttribute("isNew", false); 
        return "admin/room-form"; 
    }


     // (Admin) Xử lý cập nhật phòng

    @PostMapping("/edit/{id}")
    public String updateRoom(@PathVariable Long id, @ModelAttribute Room room, RedirectAttributes redirectAttributes) {
        try {
            
            Room existingRoom = roomService.getRoomById(id);
            
            
            existingRoom.setDescription(room.getDescription());
            existingRoom.setPrice(room.getPrice());
            existingRoom.setStatus(room.getStatus());
            existingRoom.setType(room.getType());
            
            roomService.updateRoom(id, existingRoom); 
            
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật phòng thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật phòng thất bại: " + e.getMessage());
        }
        return "redirect:/rooms/admin";
    }

   // (Admin) Xử lý xóa phòng
   
    @PostMapping("/delete/{id}") 
    public String deleteRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            roomService.deleteRoom(id); 
            redirectAttributes.addFlashAttribute("successMessage", "Xóa phòng thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa phòng: " + e.getMessage());
        }
        return "redirect:/rooms/admin"; 
    }
}
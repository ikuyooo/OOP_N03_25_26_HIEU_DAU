package com.example.quanlykhachsan;

import com.example.quanlykhachsan.model.Room;
import com.example.quanlykhachsan.model.User;
import com.example.quanlykhachsan.repository.RoomRepository; 
import com.example.quanlykhachsan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 

import java.math.BigDecimal; 
import java.util.List;     

@SpringBootApplication
public class HotelManagementApplication { 

    public static void main(String[] args) {
        SpringApplication.run(HotelManagementApplication.class, args);
    }


    @Bean
    @Autowired 
    public CommandLineRunner createDefaultData(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoomRepository roomRepository 
    ) {
        return args -> {
            
            String adminUsername = "admin";
            if (userRepository.findByUsername(adminUsername).isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername(adminUsername);
                adminUser.setPasswordHash(passwordEncoder.encode("admin123")); 
                adminUser.setRole("ROLE_ADMIN"); 
                adminUser.setActive(true);
                userRepository.save(adminUser);
                System.out.println("==================================================");
                System.out.println("!!! ĐÃ TẠO TÀI KHOẢN ADMIN: admin / admin123");
                System.out.println("==================================================");
            }
            
            System.out.println("--- Bắt đầu kiểm tra và tạo phòng mẫu (101-505)... ---");
            
            int roomsCreatedCount = 0;
            
            for (int floor = 1; floor <= 5; floor++) {
                for (int roomNum = 1; roomNum <= 5; roomNum++) {
                    
                    String roomCode = String.format("%d0%d", floor, roomNum); 
                    
                    if (!roomRepository.existsByCode(roomCode)) {
                        Room room = new Room();
                        room.setCode(roomCode);

                        String roomType;
                        if (roomNum == 3) {
                            roomType = "VIP"; // Phòng *03 là VIP
                        } else if (roomNum == 1 || roomNum == 4) {
                            roomType = "SGL"; // Phòng *01 và *04 là SGL
                        } else {
                            roomType = "DBL"; // Phòng *02 và *05 là DBL
                        }
                        room.setType(roomType);
                        
                        room.setPrice(new BigDecimal((floor * 500000) + (roomNum * 100000))); 
                        room.setStatus("VACANT"); 
                        room.setDescription("Phòng mẫu " + roomCode);
                        
                        roomRepository.save(room);
                        roomsCreatedCount++;
                    }
                }
            }
            
            if (roomsCreatedCount > 0) {
                 System.out.println("--- Đã tạo " + roomsCreatedCount + " phòng mẫu mới. ---");
            } else {
                 System.out.println("--- Tất cả phòng mẫu đã tồn tại, không tạo thêm. ---");
            }
        };
    }
}
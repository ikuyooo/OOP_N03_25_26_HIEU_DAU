package com.example.quanlykhachsan.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Lớp cấu hình chứa các Bean bổ sung cho ứng dụng.
 */
@Configuration
public class AppConfig {

    /**
     * Cung cấp một Bean ModelMapper.
     * Service của bạn có thể @Autowired ModelMapper này
     * để tự động chuyển đổi (map) giữa DTO và Entity.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
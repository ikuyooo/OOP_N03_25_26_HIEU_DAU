package com.example.quanlykhachsan.config;

import com.example.quanlykhachsan.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    private static final String ADMIN = "ADMIN";
    private static final String CUSTOMER = "CUSTOMER";

    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder; 
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); 
        authProvider.setPasswordEncoder(passwordEncoder); 
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                
                // 1. CÁC TRANG CÔNG KHAI
                .requestMatchers(
                        "/", "/home", "/login", "/register",
                        "/css/**", "/js/**", "/images/**", "/static/**",
                        "/rooms/search"
                ).permitAll()

                // 2. QUYỀN ADMIN CHUNG VÀ CẤU HÌNH
                .requestMatchers(
                    "/quanly", 
                    "/rooms/dashboard", 
                    
                    
                    "/admin/**", 
                    
                    
                    "/bookings/**", 
                    "/checkin/**",  
                    "/checkout/**"
                ).hasRole(ADMIN)

                // 3. QUYỀN KHÁCH HÀNG 
                .requestMatchers(
                    "/profile",
                    "/bookings/new", 
                    "/bookings/history", 
                    "/bookings/submit" 
                ).hasRole(CUSTOMER) 

                // 4. CÁC TRANG CÒN LẠI
                .anyRequest().authenticated()
            )
            // CẤU HÌNH ĐĂNG NHẬP 
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    // Kiểm tra quyền có tiền tố ROLE_
                    boolean isAdmin = authentication.getAuthorities().stream()
                            .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + ADMIN)); 
                    
                    if (isAdmin) {
                        response.sendRedirect("/rooms/dashboard"); 
                    } else {
                        response.sendRedirect("/"); 
                    }
                })
                .failureUrl("/login?error=true")
                .permitAll()
            )
            //  CẤU HÌNH ĐĂNG XUẤT và Provider
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .authenticationProvider(authenticationProvider())
            .csrf(csrf -> csrf.disable()); 
        
        return http.build();
    }
}
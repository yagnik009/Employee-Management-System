//package com.Backend.Employee.Management.System.Service;
//
//import com.Backend.Employee.Management.System.Entity.User;
//import com.Backend.Employee.Management.System.Repositry.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class AuthService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
////    private JwtUtil jwtUtil;
//
//    public String register(User user) {
//        if (user.getPassword() == null || user.getPassword().isEmpty()) {
//            throw new IllegalArgumentException("Password cannot be null or empty");
//        }
//
//        // Validate password complexity
//        validatePassword(user.getPassword());
//        System.out.println(user);
//
//        // Check if username already exists
//        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//            throw new RuntimeException("Username already exists");
//        }
//
//        // Encode password and save user
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//
//        // Return success message
//        return "User " + user.getUsername() + " registered successfully!";
//    }
//
//    // Password validation method
//    private void validatePassword(String password) {
//        if (password.length() < 8) {
//            throw new RuntimeException("Password must be at least 8 characters long");
//        }
////        if (!password.matches(".*[A-Z].*")) {
////            throw new RuntimeException("Password must contain at least one uppercase letter");
////        }
////        if (!password.matches(".*[a-z].*")) {
////            throw new RuntimeException("Password must contain at least one lowercase letter");
////        }
////        if (!password.matches(".*\\d.*")) {
////            throw new RuntimeException("Password must contain at least one digit");
////        }
////        if (!password.matches(".*[@#$%^&+=].*")) {
////            throw new RuntimeException("Password must contain at least one special character (@#$%^&+=)");
////        }
//    }
//
//
//    public Map<String, Object> login(String username, String password) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        System.out.println("Username: " + username);
//        System.out.println("Password: " + password);
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//        long expirationTimeMillis = 1000 * 60 * 60; // 1 घंटे
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() + expirationTimeMillis);
//
//        // JWT Token Generate करें
////        String token = jwtUtil.generateToken(user.getUsername(), user.getRole(), expirationDate);
//
////        // Token और Expiry Time को Map में डालें
////        Map<String, Object> response = new HashMap<>();
////        response.put("token", token);
////        response.put("expiryTime", expirationDate.toString());
//
//        // Generate JWT token
////        return response;
//    }
//}

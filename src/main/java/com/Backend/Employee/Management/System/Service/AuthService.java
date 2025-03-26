package com.Backend.Employee.Management.System.Service;

import com.Backend.Employee.Management.System.Entity.User;
import com.Backend.Employee.Management.System.Repositry.UserRepository;
import com.Backend.Employee.Management.System.JwtSecurity.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public String register(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        validatePassword(user.getPassword());

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User " + user.getUsername() + " registered successfully!";
    }

//    public Map<String, Object> login(String username, String password) {
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//
//            if (userDetails != null && password.equals(userDetails.getPassword())) {
//                String token = jwtHelper.generateToken((Authentication) userDetails);
//
//                String roles = userDetails.getAuthorities().toString();
//
//                response.put("token", token);
//                response.put("expiryTime", new Date(System.currentTimeMillis() + JwtHelper.JWT_TOKEN_VALIDITY * 1000));
//                response.put("roles", roles);
//                response.put("username", username);
//
//            } else {
//                throw new BadCredentialsException("Invalid username or password");
//            }
//        } catch (Exception e) {
//            response.put("error", "Authentication failed: " + e.getMessage());
//        }
//
//        return response;
//    }

        public void validatePassword(String password){
            if (password.length() < 8) {
                throw new RuntimeException("Password must be at least 8 characters long");
            }
        }
    }


package com.Backend.Employee.Management.System.Controllor;
import com.Backend.Employee.Management.System.Entity.JwtResponse;
import com.Backend.Employee.Management.System.Entity.User;
import com.Backend.Employee.Management.System.Repositry.UserRepository;
import com.Backend.Employee.Management.System.Service.AuthService;
import com.Backend.Employee.Management.System.JwtSecurity.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth/")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtHelper helper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            String message = authService.register(user);
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody User user) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = helper.generateToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            User authenticatedUser = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            JwtResponse jwtResponse = JwtResponse.builder()
                    .jwtToken(token)
                    .username(authenticatedUser.getUsername())
                    .roles(String.join(",", authenticatedUser.getRoleList()))
                    .expiryTime(new Date(System.currentTimeMillis() + JwtHelper.JWT_TOKEN_VALIDITY))
                    .build();

            return ResponseEntity.ok(jwtResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

        @PostMapping("forgot-password")
        public ResponseEntity<String> forgotPassword(@RequestParam String username) {
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
            }

            User user = userOptional.get();
            String resetToken = UUID.randomUUID().toString();
            user.setResetToken(resetToken);
            userRepository.save(user);
            return ResponseEntity.ok("Password reset token: " + resetToken);
        }


        @PostMapping("reset-password")
        public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
            Optional<User> userOptional = userRepository.findByResetToken(token);
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token!");
            }

            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            userRepository.save(user);

            return ResponseEntity.ok("Password reset successfully!");
        }
    }








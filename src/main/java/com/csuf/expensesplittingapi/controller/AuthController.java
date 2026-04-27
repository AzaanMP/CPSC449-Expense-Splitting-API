package com.csuf.expensesplittingapi.controller;

import com.csuf.expensesplittingapi.dto.AuthResponse;
import com.csuf.expensesplittingapi.dto.LoginRequest;
import com.csuf.expensesplittingapi.dto.RegisterRequest;
import com.csuf.expensesplittingapi.model.User;
import com.csuf.expensesplittingapi.repository.UserRepository;
import com.csuf.expensesplittingapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        // Prevent duplicate emails to satisfy the HTTP 409 requirement
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }

        // Hash the password with BCrypt before saving
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User(request.getUsername(), request.getEmail(), hashedPassword);
        userRepository.save(newUser);

        // Generate token and return 201 Created
        String token = jwtUtil.generateToken(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        // Validate credentials. If invalid, return a generic 401 Unauthorized message.
        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        // Generate token and return 200 OK
        String token = jwtUtil.generateToken(userOpt.get());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
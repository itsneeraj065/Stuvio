package com.stuvio.backend.controller;

import com.stuvio.backend.dto.LoginRequest;
import com.stuvio.backend.dto.LoginResponse;
import com.stuvio.backend.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        System.out.println("LOGIN API HIT");

        String username = request.getUsername();
        String password = request.getPassword();

        // TEMP LOGIN (replace with DB later)
        if ("admin".equals(username) && "1234".equals(password)) {

            String token = jwtUtil.generateToken(username);

            LoginResponse response = new LoginResponse();
            response.setMessage("Login successful");
            response.setToken(token);
            response.setEmail(username);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
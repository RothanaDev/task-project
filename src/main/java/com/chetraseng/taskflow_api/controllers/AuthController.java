package com.chetraseng.taskflow_api.controllers;

import com.chetraseng.taskflow_api.dto.requests.LoginRequest;
import com.chetraseng.taskflow_api.dto.requests.UserRequest;
import com.chetraseng.taskflow_api.dto.responses.AuthResponse;
import com.chetraseng.taskflow_api.dto.responses.UserResponse;
import com.chetraseng.taskflow_api.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
 private AuthService authService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public UserResponse signup(@Valid @RequestBody UserRequest userRequest){
        return authService.signUp(userRequest);
    }
    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}

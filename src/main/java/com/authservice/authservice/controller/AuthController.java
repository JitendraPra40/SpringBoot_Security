package com.authservice.authservice.controller;

import com.authservice.authservice.dto.APIResponse;
import com.authservice.authservice.dto.UserDto;
import com.authservice.authservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@Valid @RequestBody UserDto userDto){

        APIResponse<String> response = authService.register(userDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }


}

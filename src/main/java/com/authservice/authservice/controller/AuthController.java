package com.authservice.authservice.controller;

import com.authservice.authservice.dto.APIResponse;
import com.authservice.authservice.dto.LoginDto;
import com.authservice.authservice.dto.UserDto;
import com.authservice.authservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;
    private AuthenticationManager authManager;
    public AuthController(AuthService authService, AuthenticationManager authManager) {
        this.authService = authService;
        this.authManager = authManager;
    }


    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@Valid @RequestBody UserDto userDto){

        APIResponse<String> response = authService.register(userDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> loginCheck(@Valid @RequestBody LoginDto loginDto){

        APIResponse<String> response = new APIResponse<>();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        try {
            Authentication authenticate = authManager.authenticate(token);

            if(authenticate.isAuthenticated()) {
                response.setMessage("Login Sucessful");
                response.setStatus(200);
                response.setData("User has logged");
                return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setMessage("Failed");
        response.setStatus(401);
        response.setData("Un-Authorized Access");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

}

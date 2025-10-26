package com.authservice.authservice.service;

import com.authservice.authservice.dto.APIResponse;
import com.authservice.authservice.dto.UserDto;
import com.authservice.authservice.entity.User;
import com.authservice.authservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   public APIResponse<String> register(UserDto dto){
       APIResponse<String> response = new APIResponse<>();
       if(userRepository.existsByUsername(dto.getUsername())){
           response.setMessage("Error");
           response.setData("Username exists");
           response.setStatus(400);
           return response;
       }

       if(userRepository.existsByEmail(dto.getEmail())){
           response.setMessage("Error");
           response.setData("Email exists");
           response.setStatus(400);
           return response;
       }
       User user = new User();
       BeanUtils.copyProperties(dto,user);
       user.setPassword(passwordEncoder.encode(dto.getPassword()));
       userRepository.save(user);

       response.setMessage("Done");
       response.setData("User registered successfully");
       response.setStatus(201);

       return response;
   }
}

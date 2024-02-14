package com.example.practicingJwt.application.service;

import com.example.practicingJwt.application.repository.UserRepository;
import com.example.practicingJwt.model.payload.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailValidate implements UserRegisterValidation{

    private UserRepository userRepository;
    @Override
    public void validate(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already registered");

    }
}

package com.example.practicingJwt.application.service;

import com.example.practicingJwt.model.payload.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceimpl implements AuthService {

    private AuthenticationManager authenticationManager;

    @Override
    public String log(LoginRequest loginRequest) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserNameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "Logged-in !";
    }
}

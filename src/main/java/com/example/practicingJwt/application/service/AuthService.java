package com.example.practicingJwt.application.service;

import com.example.practicingJwt.model.payload.LoginRequest;
import com.example.practicingJwt.model.payload.RegisterRequest;

public interface AuthService {

    String log(LoginRequest loginRequest);

    String register(RegisterRequest request);

}

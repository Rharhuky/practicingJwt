package com.example.practicingJwt.application.service;

import com.example.practicingJwt.model.payload.LoginRequest;

public interface AuthService {

    String log(LoginRequest loginRequest);
}

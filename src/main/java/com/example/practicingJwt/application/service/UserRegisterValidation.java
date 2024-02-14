package com.example.practicingJwt.application.service;

import com.example.practicingJwt.model.payload.RegisterRequest;

public interface UserRegisterValidation {

    void validate(RegisterRequest request);

}

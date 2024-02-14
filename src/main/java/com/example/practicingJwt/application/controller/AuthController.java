package com.example.practicingJwt.application.controller;

import com.example.practicingJwt.application.service.AuthService;
import com.example.practicingJwt.model.payload.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/login", "signin"})
public class AuthController {

    private AuthService authService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        var response = authService.log(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}

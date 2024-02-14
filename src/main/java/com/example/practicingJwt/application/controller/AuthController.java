package com.example.practicingJwt.application.controller;

import com.example.practicingJwt.application.service.AuthService;
import com.example.practicingJwt.model.payload.LoginRequest;
import com.example.practicingJwt.model.payload.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {

    private AuthService authService;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        var response = authService.log(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        var response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}

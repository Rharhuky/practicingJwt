package com.example.practicingJwt.application.service;

import com.example.practicingJwt.application.authentication.Role;
import com.example.practicingJwt.application.authentication.User;
import com.example.practicingJwt.application.repository.RoleRepository;
import com.example.practicingJwt.application.repository.UserRepository;
import com.example.practicingJwt.model.payload.LoginRequest;
import com.example.practicingJwt.model.payload.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthServiceimpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private List<UserRegisterValidation> userRegisterValidationList;

    @Override
    public String log(LoginRequest loginRequest) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserNameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "Logged-in !";
    }

    @Override
    public String register(RegisterRequest request) { // fixme

        userRegisterValidationList.forEach((validator) -> validator.validate(request));

        var newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setUserName(request.getUserName());
        newUser.setName(request.getName());
        newUser.setPassword( passwordEncoder.encode(request.getPassword()));

        var userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("ROLE not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        newUser.setRoles(roles);

        userRepository.save(newUser);

        return "Registered !";
    }
}

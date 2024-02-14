package com.example.practicingJwt.application.security;

import com.example.practicingJwt.application.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var theUser = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Set<GrantedAuthority> userAuthority = theUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new User(theUser.getEmail(), theUser.getPassword(), userAuthority);
    }
}

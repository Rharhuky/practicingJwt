package com.example.practicingJwt.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> //authorize.anyRequest().authenticated()
                    authorize.requestMatchers(HttpMethod.GET).permitAll()
                            .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin = User
//                .builder()
//                .username("admin")
//                .password( passwordEncoder().encode("admin"))
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails rharhuky = User.builder()
//                .username("rharhuky")
//                .password( passwordEncoder().encode("rharhuky"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, rharhuky);
//    }

}

package com.example.practicingJwt.application.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("app.jwt.expiration.milliseconds")
    private long jwtExpirationDate;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date theCurrentDate = new Date();
        Date expireDate = new Date(theCurrentDate.getTime() + jwtExpirationDate);

        String theToken = Jwts.builder().subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(generateKey())
                .compact();

        return theToken;
    }

    // get Username from JWT token

    public String getUsername(String token){

        return Jwts.parser().verifyWith( (SecretKey) generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateTokenJwt(String token){

        try{
            Jwts.parser()
                    .verifyWith((SecretKey) generateKey())
                    .build()
                    .parse(token);
            return true;
        }
        catch (MalformedJwtException | IllegalArgumentException |ExpiredJwtException malformedJwtException){
            throw new RuntimeException();

        }
        catch (UnsupportedJwtException unsupportedJwtException){
            throw new RuntimeException();
        }

    }

    private Key generateKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }
}

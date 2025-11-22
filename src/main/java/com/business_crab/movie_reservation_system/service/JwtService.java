package com.business_crab.movie_reservation_system.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.business_crab.movie_reservation_system.model.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
@Scope(value="singleton")
public class JwtService {
    private final SecretKey secretKey;

    public JwtService() {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
        keyGenerator.init(256);
        secretKey = keyGenerator.generateKey();
    }

    public String generateToken(final User user) {
        return Jwts.builder()
                   .subject(user.getUsername())
                   .claims(Map.of("ROLES" , user.getAuthorities()))
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(new Date(System.currentTimeMillis() + 30 * 60 * 60 * 1000))
                   .signWith(secretKey)
                   .compact();
    }

    public Claims extractAllClaims(final String token) {
        return (Claims) Jwts.parser()
                           .verifyWith(secretKey)
                           .build()
                           .parse(token)
                           .getPayload();
    }

    public String extractUsername(final String token) {
        return extractAllClaims(token).getSubject();
    }
}
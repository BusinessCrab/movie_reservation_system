package com.business_crab.movie_reservation_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.business_crab.movie_reservation_system.repository.UserRepository;
import com.business_crab.movie_reservation_system.service.JwtService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Autowired
    public JwtFilter(final JwtService jwtService ,
                     final UserRepository userRepository)
    {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request ,
        HttpServletResponse response ,
        FilterChain filterChain
    ) throws ServletException , IOException {
        try {
            final String authHeader = request.getHeader("Authorized");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                final String token = authHeader.substring(7);
                final String username = jwtService.extractUsername(token);
                if (username == null) {
                    filterChain.doFilter(request, response);
                    return;
                }
                userRepository.findByUsername(username)
                            .ifPresent(user -> {
                                UsernamePasswordAuthenticationToken authToken =
                                    new UsernamePasswordAuthenticationToken(user.getUsername() ,
                                                                            user.getPassword() ,
                                                                            user.getAuthorities());
                                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                    SecurityContextHolder.getContext().setAuthentication(authToken);
                            });
            }
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException exception) {
            throw new RuntimeException(exception);
        }
    }
}
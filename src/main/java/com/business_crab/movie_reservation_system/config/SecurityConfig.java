package com.business_crab.movie_reservation_system.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.business_crab.movie_reservation_system.model.entities.User;
import com.business_crab.movie_reservation_system.repository.UserRepository;

@Configuration
@EnableMethodSecurity(securedEnabled=true)
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(final UserRepository userRepository ,
                          final JwtFilter jwtFilter)
    {
        this.userRepository = userRepository;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(httpRequestCustomizer -> {
                        httpRequestCustomizer.requestMatchers("/auth/**")
                                             .permitAll()
                                             .anyRequest()
                                             .authenticated();
                    })
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                return user.get();
            }
            throw new UsernameNotFoundException("User is not found");
        };
    }
}
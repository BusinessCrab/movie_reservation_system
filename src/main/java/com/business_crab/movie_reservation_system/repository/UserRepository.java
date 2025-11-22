package com.business_crab.movie_reservation_system.repository;

import java.util.Optional;

import com.business_crab.movie_reservation_system.model.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    public Optional<User> findByUsername(final String username);
}
package com.business_crab.movie_reservation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business_crab.movie_reservation_system.model.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{ }
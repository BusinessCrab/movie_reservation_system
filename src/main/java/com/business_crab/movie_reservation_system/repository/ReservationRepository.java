package com.business_crab.movie_reservation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business_crab.movie_reservation_system.model.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{ }
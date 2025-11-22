package com.business_crab.movie_reservation_system.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.business_crab.movie_reservation_system.model.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie , Long>{ }
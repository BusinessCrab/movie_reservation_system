package com.business_crab.movie_reservation_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business_crab.movie_reservation_system.model.entities.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    public Page<Theater> findAllByLocation(final String location ,
                                           final Pageable pageable);
}
package com.business_crab.movie_reservation_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business_crab.movie_reservation_system.model.entities.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    public Page<Show> findByTheaterId(final Long theatreId ,
                                      final Pageable pageable);
    public Page<Show> findByMovieId(final Long movieId ,
                                    final Pageable pageable);
    public Page<Show> findByTheaterIdAndMovieId(final Long theaterId ,
                                                final Long movieId ,
                                                final Pageable pageable);
}
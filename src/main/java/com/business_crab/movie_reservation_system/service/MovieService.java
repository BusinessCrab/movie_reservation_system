package com.business_crab.movie_reservation_system.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business_crab.movie_reservation_system.model.dto.MovieRequestDTO;
import com.business_crab.movie_reservation_system.model.entities.Movie;
import com.business_crab.movie_reservation_system.repository.MovieRepository;
import com.business_crab.movie_reservation_system.util.enums.MovieGenre;
import com.business_crab.movie_reservation_system.util.exceptions.MovieNotFoundException;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> getAllMovies(final Integer page , final Integer pageSize) {
        return movieRepository.findAll(PageRequest.of(page , pageSize));
    }

    public Movie getMovieById(final Long id) {
        return movieRepository.findById(id)
                              .orElseThrow(() -> new MovieNotFoundException("Movie is not found", HttpStatus.NOT_FOUND));
    }

    public Movie createNewMovie(final MovieRequestDTO MovieRequestDTO) {
        Movie movie = Movie.builder()
                           .movieLanguage(MovieRequestDTO.getMovieLanguage())
                           .movieLength(MovieRequestDTO.getMovieLength())
                           .genre(MovieRequestDTO.getGenre().stream().map(MovieGenre::valueOf).toList())
                           .movieName(MovieRequestDTO.getMovieName())
                           .releaseDate(LocalDate.parse(MovieRequestDTO.getReleaseDate()))
                           .build();
        return movieRepository.save(movie);
    }

    public Movie updateMovieById(final Long movieId , final MovieRequestDTO movieRequestDTO) {
        return movieRepository.findById(movieId)
                              .map(movie -> {
                                movie.setMovieName(movieRequestDTO.getMovieName());
                                movie.setGenre(movieRequestDTO.getGenre().stream().map(MovieGenre::valueOf).toList());
                                movie.setMovieLanguage(movieRequestDTO.getMovieLanguage());
                                movie.setReleaseDate(LocalDate.parse(movieRequestDTO.getReleaseDate()));
                                movie.setMovieLength(movieRequestDTO.getMovieLength());

                                return movieRepository.save(movie);
                              })
                              .orElseThrow(() -> new MovieNotFoundException("Movie is not found", HttpStatus.NOT_FOUND));
    }

    public void deleteMovieById(final Long movieId) {
        movieRepository.deleteById(movieId);
    }
}

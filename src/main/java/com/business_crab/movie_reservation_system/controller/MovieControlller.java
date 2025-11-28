package com.business_crab.movie_reservation_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business_crab.movie_reservation_system.model.dto.ApiResponseDTO;
import com.business_crab.movie_reservation_system.model.dto.MovieRequestDTO;
import com.business_crab.movie_reservation_system.model.dto.PagedApiRequestDTO;
import com.business_crab.movie_reservation_system.model.entities.Movie;
import com.business_crab.movie_reservation_system.service.MovieService;

import jdk.jfr.Description;


@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
@RestController
@RequestMapping("/api/v1/movies")
@Description(value="Rest Controller for movie managment. Can change only by admin or superadmin")
public class MovieControlller {
    private final MovieService movieService;
    
    @Autowired
    public MovieControlller(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<PagedApiRequestDTO> getAllMovies(final @RequestParam(defaultValue="0") Integer page ,
                                         final @RequestParam(defaultValue="10") Integer pageSize)
    {
        Page<Movie> moviePage = movieService.getAllMovies(page, pageSize);
        List<Movie> movies = moviePage.getContent();
        return ResponseEntity.ok(
            PagedApiRequestDTO.builder()
                              .totalPages(moviePage.getTotalPages())
                              .totalElements(moviePage.getTotalElements())
                              .currentPageData(movies)
                              .build()
        );
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<ApiResponseDTO> getMovieById(final @PathVariable Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return ResponseEntity.ok(
            ApiResponseDTO.builder()
                          //.message("Fetch movie with id: " + movieId)
                          //.data(movie)
                          .build()
        );
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO> createNewMovie(final @RequestBody MovieRequestDTO movieRequestDTO) {
        Movie movie = movieService.createNewMovie(movieRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(
                                ApiResponseDTO.builder()
                                              //.message("Movie is created")
                                              //.data(movie)
                                              .build()
                             );
    }

    @PutMapping("/movie/update/{movieId}")
    public ResponseEntity<ApiResponseDTO> updateMovieById(final @PathVariable Long movieId ,
                                                          final @RequestBody MovieRequestDTO movieRequestDTO)
    {
        final Movie movie = movieService.updateMovieById(movieId , movieRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(
                                ApiResponseDTO.builder()
                                              //.message("Movie is updated")
                                              //.data(movie)
                                              .build()
                             );
    }

    @DeleteMapping("/movie/delete/{movieId}")
    public ResponseEntity<?> deleteMovieById(final @PathVariable Long movieId) {
        movieService.deleteMovieById(movieId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .build();
    }
}

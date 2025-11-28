package com.business_crab.movie_reservation_system.model.entities;

import java.time.LocalDate;
import java.util.List;

import com.business_crab.movie_reservation_system.util.enums.MovieGenre;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String movieName;

    @Enumerated(value = EnumType.STRING)
    private List<MovieGenre> genre;
    private Integer movieLength;
    private String movieLanguage;
    private LocalDate releaseDate;
}
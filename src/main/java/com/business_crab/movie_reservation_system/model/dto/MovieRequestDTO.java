package com.business_crab.movie_reservation_system.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRequestDTO {
    private String movieName;
    private List<String> genre;
    private Integer movieLength;
    private String movieLanguage;
    private String releaseDate;
}
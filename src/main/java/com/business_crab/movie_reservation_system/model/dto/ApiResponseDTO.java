package com.business_crab.movie_reservation_system.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDTO {
    private String token;
}
package com.business_crab.movie_reservation_system.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PagedApiRequestDTO {
    private Integer totalPages;
    private Long totalElements;
    private List<?> currentPageData;
    private Integer currentCount;
}
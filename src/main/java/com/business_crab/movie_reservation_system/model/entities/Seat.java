package com.business_crab.movie_reservation_system.model.entities;

import com.business_crab.movie_reservation_system.util.enums.SeatStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="seats")
public class Seat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value=EnumType.STRING)
    private SeatStatus seatStatus;
    private Double price;
    private Integer number;
    private String area;
}
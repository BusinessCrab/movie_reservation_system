package com.business_crab.movie_reservation_system.model.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.business_crab.movie_reservation_system.util.enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Seat> seatsReserved;
    private Double amountPaid;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus reservationStatus;
    private LocalDateTime createdAt;
}
package com.business_crab.movie_reservation_system.model.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="shows")
public class Show {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity=Movie.class)
    @JoinColumn(referencedColumnName="id" , nullable=false)
    private Movie movie;

    @ManyToOne
    private Theater theater;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany(fetch=FetchType.LAZY , cascade=CascadeType.REMOVE)
    private List<Seat> seats;
}
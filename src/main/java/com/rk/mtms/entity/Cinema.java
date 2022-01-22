package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cinema")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Cinema implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="cinema_name")
    private String cinemaName;
    @Column(name="total_cinema_hall")
    private int totalCinemaHall;
    @Column(name="cinema_address")
    private String cinemaAddress;
    @Column(name = "movie_id")
    private int movieId;
}

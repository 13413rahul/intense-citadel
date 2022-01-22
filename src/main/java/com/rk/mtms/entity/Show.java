package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="shown")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Show implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="movie_id")
    private int movieId;
    @Column(name="start_time")
    private int startTime;
    @Column(name="cinema_hall_id")
    private int cinemaHallId;

}

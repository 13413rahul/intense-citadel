package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cinema_hall")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class CinemaHall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="cinema_id")
    private int cinemaId;
    @Column(name="cinema_hall_name")
    private String cinemaHallName;
    @Column(name="total_seat")
    private int totalSeat;
}

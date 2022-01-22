package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="seat")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Seat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="seat_no")
    private int seatNo;
    @Column(name="status")
    private String status;
    @Column(name="hall_id")
    private int hallId;
    @Column(name="seat_type")
    private String seatType;
    @Column(name="price_id")
    private int priceId;
    @Column(name="rown")
    private int row;
    @Column(name="colomn")
    private int colomn;
    @Column(name="show_id")
    private int showId;
}

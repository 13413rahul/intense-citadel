package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="booking")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="seat_list")
    private String seatList;

    @Column(name="status")
    private String status;

    @Column(name="amount")
    private int amount;

    @Column(name="show_id")
    private int showId;

    @Column(name="receipt_id")
    private int receiptId;

    @Column(name="user_id")
    private int userId;

    @Column(name="type_booked")
    private String typeBooked;

}

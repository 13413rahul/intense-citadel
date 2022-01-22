package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="receipt")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Receipt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="total_amount")
    private int totalAmount;

    @Column(name="payment_id")
    private int paymentId;

    @Column(name="type_booked")
    private String typeBooked;

    @Column(name="total_booked_seat")
    private int totalBookedSeat;
}

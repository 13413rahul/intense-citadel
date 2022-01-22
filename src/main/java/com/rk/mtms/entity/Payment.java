package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="payment")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="payment_type")
    private String paymentType;
    @Column(name="payment_status")
    private String paymentStatus;
    @Column(name="amount")
    private int amount;
    @Column(name="transaction_id")
    private int transactionId;

}

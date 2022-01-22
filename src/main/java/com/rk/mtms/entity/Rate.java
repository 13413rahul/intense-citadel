package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="rate")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Rate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="movie_id")
    private int movieId;
    @Column(name="rated_by")
    private int ratedBy;
    @Column(name="rate")
    private float rate;
}

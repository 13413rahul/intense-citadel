package com.rk.mtms.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="address")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="city_name")
    private String cityName;
    @Column(name="state_name")
    private String stateName;
    @Column(name="country_name")
    private String countryName;
    @Column(name="pin_no")
    private int pinNo;
    @Column(name="name")
    private String name;

}

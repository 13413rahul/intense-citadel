package com.rk.mtms.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name="movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="movie_name")
    private String movieName;

    @Column(name="language")
    private String language;

    @Column(name="price")
    private float price;

    @Column(name="released_data_string")
    private String releasedDataString;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="released_data")
    private LocalDate releasedData;

    @Column(name="duration")
    private int duration;

    @Column(name="genre_type")
    private String genreType;

    @Column(name="actor_id")
    private int actorId;

    @Column(name="description")
    private String description;

    @Column(name="rate_id")
    private int rateId;

    @Column(name="image_url")
    private String imageUrl;

}

package com.rk.mtms.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MovieDetailResponse {
    private String movieName;
    private String photo;
    private float rate;
    private String description;
    private String genreType;
    private int duration;
    private Timestamp releasedData;
    private float price;
    private String language;
    private String cinemaAddress;
    private String cinemaName;
    private int totalCinemaHall;
    private String actorName;

}

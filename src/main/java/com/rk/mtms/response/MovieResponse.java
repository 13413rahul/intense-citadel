package com.rk.mtms.response;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class MovieResponse {
     private int id;

     private String movieName;

     private String language;

     private float price;

     private Timestamp releasedData;

     private int duration;

     private String genreType;

     private int actorName;

     private String description;

     private int rate;

     private String imageUrl;

     String key;

}

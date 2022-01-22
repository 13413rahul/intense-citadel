package com.rk.mtms.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddMovieRequest {
    String movieName;
    String language;
    float price;
    int duration;
    String genreType;
    String actorName;
    String description;
    String releasedDateString;
    LocalDate releasedDate;
    float rate;
    int ratedBy;
}

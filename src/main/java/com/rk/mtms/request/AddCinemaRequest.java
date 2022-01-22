package com.rk.mtms.request;

import lombok.Data;

import javax.persistence.*;

@Data
public class AddCinemaRequest {

    private String cinemaName;
    private int totalCinemaHall;
    private String cinemaAddress;
    private String movieName;

}

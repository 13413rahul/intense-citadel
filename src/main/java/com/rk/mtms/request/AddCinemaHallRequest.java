package com.rk.mtms.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class AddCinemaHallRequest {

    private String cinemaName;

    private String cinemaHallName;

    private int totalSeat;
}

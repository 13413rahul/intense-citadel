package com.rk.mtms.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class AddShowRequest {

    private String movieName;

    private int startTime;

    private String cinemaHallName;
}

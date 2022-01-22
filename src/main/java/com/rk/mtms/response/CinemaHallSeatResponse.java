package com.rk.mtms.response;

import lombok.Data;

@Data
public class CinemaHallSeatResponse {
    private int seatId;
    private int seatNo;
    private String seatType;
    private int price;
    private int row;
    private int column;

}

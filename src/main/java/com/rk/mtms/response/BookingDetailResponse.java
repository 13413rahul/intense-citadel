package com.rk.mtms.response;

import lombok.Data;

@Data
public class BookingDetailResponse {
    String userName;
    String MovieName;
    String cinemaName;
    String cinemaHallName;
    String typeBooked;
    String seatList;
    int amount;
    int startTime;
    int duration;
}

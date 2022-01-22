package com.rk.mtms.request;

import lombok.Data;
import java.util.List;

@Data
public class BookingRequest {
    String userName;
    String movieName;
    String cinemaName;
    String cinemaHallName;
    int amount;
    String bookingType;
    List<Integer> seatList;
}

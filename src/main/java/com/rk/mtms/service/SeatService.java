package com.rk.mtms.service;

import com.rk.mtms.entity.Seat;
import org.springframework.stereotype.Service;


@Service
public interface SeatService {
    void addSeat(Seat seat,int noOfSeats);

    void deleteShow(Seat seat, int totalSeat);

    void updateSeat(int seatList, String status);
}

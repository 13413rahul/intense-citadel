package com.rk.mtms.serviceImpl;

import com.rk.mtms.entity.Seat;
import com.rk.mtms.repository.CinemaHallSeatRepository;
import com.rk.mtms.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatServiceImpl implements SeatService {

    @Autowired
    CinemaHallSeatRepository cinemaHallSeatRepository;

    @Override
    public void addSeat(Seat seat, int noOfSeats) {
        Seat seatEntity = null;
        int row = 1;
        int column = 1;
        for (int seatNumber = 1; seatNumber <= noOfSeats; seatNumber++) {
            seatEntity = new Seat();
            System.out.println(seatNumber);
            seatEntity.setHallId(seat.getHallId());
            seatEntity.setSeatType(seat.getSeatType());
            seatEntity.setStatus(seat.getStatus());
            seatEntity.setPriceId(seat.getPriceId());
            if(column == 4) {
                row++;
                column = 1;
            }
            seatEntity.setColomn(column);
            seatEntity.setRow(row);
            seatEntity.setSeatNo(column * 10 + row);
            column++;
            seatEntity.setShowId(seat.getShowId());
            cinemaHallSeatRepository.save(seatEntity);
        }

    }

    @Override
    public void deleteShow(Seat seat, int totalSeat) {
        cinemaHallSeatRepository.deleteByShowId(seat.getShowId());
    }

    @Override
    public void updateSeat(int seatList, String status) {
        cinemaHallSeatRepository.updateSeat(seatList, status);
    }

}

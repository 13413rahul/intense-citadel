package com.rk.mtms.repository;

import com.rk.mtms.entity.Seat;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CinemaHallSeatRepository extends CrudRepository<Seat,Integer> {
    @Query("SELECT u from Seat u where u.hallId=:RequestedHallId and u.status = 'Available' ")
    List<Seat> getDetailForSeat(@Param("RequestedHallId") int RequestedHallId);

    @Transactional
    void deleteByShowId(int showId);

    @Modifying
    @Query("UPDATE Seat s SET s.status = :status WHERE s.id = :seatId")
    void updateSeat(@Param("seatId") int seatId, @Param("status") String status);
}

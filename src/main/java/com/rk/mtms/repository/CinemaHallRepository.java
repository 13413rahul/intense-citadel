package com.rk.mtms.repository;

import com.rk.mtms.entity.CinemaHall;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CinemaHallRepository extends CrudRepository<CinemaHall,Long> {
    @Query("SELECT u from CinemaHall u where u.cinemaId=:RequestedCinemaId")
    CinemaHall getHallForCinemaId(@Param("RequestedCinemaId") int RequestedCinemaId);

    @Query("SELECT u from CinemaHall u where u.cinemaId=:RequestedCinemaId")
    List<CinemaHall> getAllCinemaHallForCinemaId(@Param("RequestedCinemaId") int RequestedCinemaId);

    @Query("SELECT u from CinemaHall u where u.cinemaHallName=:RequestedCinemaHallName")
    CinemaHall getHallForCinemaHallName(@Param("RequestedCinemaHallName") String RequestedCinemaHallName);

    @Query("SELECT u from CinemaHall u where u.id=:RequestedCinemaHallId")
    CinemaHall getHallForCinemaHallId(@Param("RequestedCinemaHallId") int RequestedCinemaHallId);

    @Modifying
    @Query(value = "insert into cinema_hall(cinema_id,cinema_hall_name,total_seat) values(:cinemaId,:cinemaHallName,:totalSeat)",nativeQuery = true)
    @Transactional
    void addCinemaHall(@Param("cinemaId") int cinemaId,@Param("cinemaHallName") String cinemaHallName,@Param("totalSeat") int totalSeat);


    CinemaHall findCinemaHallByCinemaIdAndCinemaHallName(int id, String cinemaHallName);
}

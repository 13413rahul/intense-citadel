package com.rk.mtms.repository;

import com.rk.mtms.entity.Cinema;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface CinemaRepository extends CrudRepository<Cinema,Long> {
    @Query("SELECT u from Cinema u where u.movieId=:RequestedMovieId")
    List<Cinema> getCinemaForMovieId(@Param("RequestedMovieId") int RequestedMovieId);

    @Query("SELECT u from Cinema u where u.id=:RequestedCinemaId")
    Cinema getCinemaForCinemaId(@Param("RequestedCinemaId") int RequestedCinemaId);

    @Query("SELECT u from Cinema u where u.cinemaName=:RequestedCinemaName")
    Cinema getCinemaForCinemaName(@Param("RequestedCinemaName") String RequestedCinemaName);

    @Modifying
    @Query(value = "insert into Cinema(cinema_name,cinema_address,movie_id,total_cinema_hall) values(:cinemaName,:cinemaAddress,:movieId,:totalCinemaHall)",nativeQuery = true)
    @Transactional
    void addCinema(@Param("cinemaName") String cinemaName,@Param("cinemaAddress") String cinemaAddress,@Param("movieId") int movieId,@Param("totalCinemaHall") int totalCinemaHall);

}

package com.rk.mtms.repository;

import com.rk.mtms.entity.Show;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShowRepository extends CrudRepository<Show,Long> {
    @Query("SELECT u from Show u where u.movieId=:requestedMovieId")
    List<Show> getShowIdForMovieId(@Param("requestedMovieId") int requestedMovieId);

    @Query("SELECT u from Show u where u.id=:requestedShowId")
    Show getShowForShowId(@Param("requestedShowId") int requestedShowId);

    @Modifying
    @Query(value = "insert into shown(movie_id,start_time,cinema_hall_id) values(:movieId,:startTime,:cinemaHallId)",nativeQuery = true)
    @Transactional
    void addShow(@Param("movieId") int movieId,@Param("startTime") int startTime,@Param("cinemaHallId") int cinemaHallId);

    @Modifying
    @Query(value = "DELETE FROM Shown WHERE movie_id = :RequestedMovieId AND cinema_hall_id = :RequestedCinemaHallId", nativeQuery = true)
    @Transactional
    void deleteShow(@Param("RequestedMovieId") int RequestedMovieId,@Param("RequestedCinemaHallId") int RequestedCinemaHallId);

    Show findShowByMovieIdAndCinemaHallId(int id, int id1);

    boolean existsByMovieId(int id);

    @Query("SELECT u from Show u ")
    List<Show> getShow();

    boolean existsByCinemaHallId(int id);
}

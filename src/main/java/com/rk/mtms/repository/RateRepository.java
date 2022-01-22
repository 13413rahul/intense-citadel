package com.rk.mtms.repository;


import com.rk.mtms.entity.Rate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RateRepository extends CrudRepository<Rate,Long> {
    @Query("SELECT u from Rate u where u.movieId=:RequestedMovieId")
    Rate getRateForCinema(@Param("RequestedMovieId") int RequestedMovieId);

    @Query("SELECT u from Rate u where u.rate=:RequestedRate AND u.ratedBy=:RequestedRatedBy")
    Rate getRateForRate(@Param("RequestedRate") float RequestedRate,@Param("RequestedRatedBy") int RequestedRatedBy);

    @Modifying
    @Query(value="insert into Rate(movie_id,rated_by,rate) VALUES(:movieId,:ratedBy,:rate)",nativeQuery = true)
    @Transactional
    void insertRate(@Param("movieId") int movieId,@Param("ratedBy") int ratedBy,@Param("rate") float rate);

    @Modifying
    @Query(value = "UPDATE Rate SET movie_id = :RequestedMovieId WHERE id = :RequestedId", nativeQuery = true)
    @Transactional
    void updateMovieId(@Param("RequestedMovieId") int RequestedMovieId,@Param("RequestedId") int RequestedId);

}

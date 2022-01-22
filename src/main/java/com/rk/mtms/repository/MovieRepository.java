package com.rk.mtms.repository;

import com.rk.mtms.entity.Movie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public interface MovieRepository extends CrudRepository<Movie,Long> {
    @Query("SELECT u from Movie u where u.actorId=:RequestedActorId")
    List<Movie> findByActorId(@Param("RequestedActorId") Integer RequestedActorId);


    @Query("SELECT u from Movie u where u.movieName=:RequestedMovieName")
    Movie getMovieForMovieName(@Param("RequestedMovieName") String RequestedMovieName);

    @Query("SELECT u from Movie u where u.id=:RequestedMovieId")
    Movie getMovieForMovieId(@Param("RequestedMovieId") int RequestedMovieId);

    @Query("SELECT u from Movie u where u.genreType=:RequestedUserName")
    List<Movie> getMovieForGenreType(@Param("RequestedUserName") String RequestedUserName);

    @Query("SELECT u from Movie u where u.language=:requestedMovie")
    List<Movie> getMovieForLanguage(@Param("requestedMovie") String requestedMovie);

//    @Query("SELECT u from Movie u where u.language=:requestedMovie")
//    List<Movie> getMovieForMovieName(@Param("requestedMovie") String requestedMovie);

    @Modifying
    @Query(value = "insert into Movie(movie_name,language,price,rate_id,genre_type,duration,description,actor_id,released_data_string,released_data) " +
            "values(:movieName,:language,:price,:rateId,:genreType,:duration,:description,:actorId,:releasedDateString,:releasedDate)",nativeQuery = true)
    @Transactional
    void addMovie(@Param("movieName") String movieName,@Param("language") String language,@Param("price") float price,
                  @Param("rateId") int rateId,@Param("genreType") String genreType,@Param("duration") int duration,
                  @Param("description") String description,@Param("actorId") int actorId,
                  @Param("releasedDateString") String releasedDateString,@Param("releasedDate") LocalDate releasedDate);

    @Modifying
    @Query(value = "DELETE FROM Movie WHERE movie_name = :RequestedMovieName", nativeQuery = true)
    @Transactional
    void deleteMovie(@Param("RequestedMovieName") String RequestedMovieName);

}

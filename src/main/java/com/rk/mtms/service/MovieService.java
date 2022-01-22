package com.rk.mtms.service;

import com.rk.mtms.request.MovieRequest;
import com.rk.mtms.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    ResponseEntity<List<MovieResponse>> movieList(MovieRequest movieRequest);

    ResponseEntity<List<ActorResponse>> actorList();

    ResponseEntity<MovieDetailResponse> movieDetails(MovieRequest movieRequest);

    ResponseEntity<List<CinemaHallResponse>> cinemaHallDetail(String cinemaName);

    ResponseEntity<List<CinemaHallSeatResponse>> seatDetail(String cinemaName, String cinemaHallName);

    ResponseEntity<List<CinemaResponse>> cinemaList(String movieName);

    ResponseEntity<List<MovieResponse>> allMovieList();
}

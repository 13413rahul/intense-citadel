package com.rk.mtms.controller;

import com.rk.mtms.request.MovieRequest;
import com.rk.mtms.response.*;
import com.rk.mtms.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/movieList", method = RequestMethod.POST)
    public ResponseEntity<List<MovieResponse>> getListOfMovie(@RequestBody MovieRequest movieRequest) {
        return  movieService.movieList(movieRequest);
    }

    @RequestMapping(value = "/allMovies", method = RequestMethod.GET)
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return  movieService.allMovieList();
    }

    @RequestMapping(value = "/actorList", method = RequestMethod.POST)
    public ResponseEntity<List<ActorResponse>> getListOfActor() {
        return  movieService.actorList();

    }

    @RequestMapping(value = "/movieDetails", method = RequestMethod.POST)
    public ResponseEntity<MovieDetailResponse> getMovieDetails(@RequestBody MovieRequest movieRequest) {
        return movieService.movieDetails(movieRequest);

    }

    @RequestMapping(value = "/cinema/{movieName}", method = RequestMethod.GET)
    public ResponseEntity<List<CinemaResponse>> getCinema(@PathVariable("movieName") String movieName) {
        return movieService.cinemaList(movieName);
    }

    @RequestMapping(value = "/cinemaHall/{cinemaName}", method = RequestMethod.GET)
    public ResponseEntity<List<CinemaHallResponse>> getCinemaHall(@PathVariable("cinemaName") String cinemaName) {
        return movieService.cinemaHallDetail(cinemaName);
    }

    @RequestMapping(value = "/cinemaHallSeat/{cinemaHallName}/{cinemaName}", method = RequestMethod.GET)
    public ResponseEntity<List<CinemaHallSeatResponse>> getCinemaHallSeat(@PathVariable("cinemaHallName") String cinemaHallName,
                            @PathVariable("cinemaName") String cinemaName) {
        return movieService.seatDetail(cinemaName, cinemaHallName);
    }

}

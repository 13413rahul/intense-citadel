package com.rk.mtms.serviceImpl;

import com.rk.mtms.entity.*;
import com.rk.mtms.repository.*;
import com.rk.mtms.request.CinemaHallRequest;
import com.rk.mtms.request.CinemaHallSeatRequest;
import com.rk.mtms.request.MovieRequest;
import com.rk.mtms.response.*;
import com.rk.mtms.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    RateRepository rateRepository;
    @Autowired
    CinemaHallRepository cinemaHallRepository;
    @Autowired
    CinemaHallSeatRepository cinemaHallSeatRepository;

    @Override
    public ResponseEntity<List<MovieResponse>> movieList(MovieRequest movieRequest) {
        MovieResponse movieResponse = null;
        List<MovieResponse> movieResponseList = new ArrayList<>();
        if(actorRepository.getActorForId(movieRequest.getKey()) != null) {
            Actor actor = actorRepository.getActorForId(movieRequest.getKey());
            List<Movie> movieList = movieRepository.findByActorId(actor.getId());
            if(movieList.size() != 0) {
                for (Movie movie : movieList) {
                    if(showRepository.existsByMovieId(movie.getId())) {
                        movieResponse = new MovieResponse();

                        movieResponse.setMovieName(movie.getMovieName());
                        movieResponse.setImageUrl(movie.getImageUrl());
//                movieResponse.setLanguage(movie.getLanguage());
//                movieResponse.setDescription(movie.getDescription());
                        movieResponse.setGenreType(movie.getGenreType());
//                movieResponse.setDuration(movie.getDuration());
//                movieResponse.setPrice(movie.getPrice());

                        movieResponseList.add(movieResponse);
                    }

                }
            }
        }

        if(movieRepository.getMovieForMovieName(movieRequest.getKey()) != null) {
            Movie movie = movieRepository.getMovieForMovieName(movieRequest.getKey());

            if(showRepository.existsByMovieId(movie.getId())) {
                movieResponse = new MovieResponse();

                movieResponse.setMovieName(movie.getMovieName());
                movieResponse.setImageUrl(movie.getImageUrl());
//                movieResponse.setLanguage(movie.getLanguage());
//                movieResponse.setDescription(movie.getDescription());
                movieResponse.setGenreType(movie.getGenreType());
//                movieResponse.setDuration(movie.getDuration());
//                movieResponse.setPrice(movie.getPrice());

                movieResponseList.add(movieResponse);
            }
        }

        if(movieRepository.getMovieForLanguage(movieRequest.getKey()) != null) {
            List<Movie> movieList = movieRepository.getMovieForLanguage(movieRequest.getKey());

            for(Movie movie : movieList) {
                if(showRepository.existsByMovieId(movie.getId())) {
                    movieResponse = new MovieResponse();

                    movieResponse.setMovieName(movie.getMovieName());
//                movieResponse.setLanguage(movie.getLanguage());
//                movieResponse.setDescription(movie.getDescription());
                    movieResponse.setGenreType(movie.getGenreType());
                    movieResponse.setImageUrl(movie.getImageUrl());
//                movieResponse.setDuration(movie.getDuration());
//                movieResponse.setPrice(movie.getPrice());

                    movieResponseList.add(movieResponse);
                }
            }
        }

        if(movieRepository.getMovieForGenreType(movieRequest.getKey()) != null) {
            List<Movie> movieList = movieRepository.getMovieForGenreType(movieRequest.getKey());

            for(Movie movie : movieList) {
                if(showRepository.existsByMovieId(movie.getId())) {
                    movieResponse = new MovieResponse();

                    movieResponse.setMovieName(movie.getMovieName());
//                movieResponse.setLanguage(movie.getLanguage());
//                movieResponse.setDescription(movie.getDescription());
                    movieResponse.setGenreType(movie.getGenreType());
                    movieResponse.setImageUrl(movie.getImageUrl());
//                movieResponse.setDuration(movie.getDuration());
//                movieResponse.setPrice(movie.getPrice());

                    movieResponseList.add(movieResponse);
                }
            }
        }
        return  new ResponseEntity<>(movieResponseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MovieResponse>> allMovieList() {

        List<MovieResponse> movieResponseList = new ArrayList<>();
        MovieResponse movieResponse = null;
        List<Show> showList = showRepository.getShow();
        Set<Integer> movieIdSet = new HashSet<>();

        if(showList.size() != 0) {
            for (Show  show: showList) {
                Movie movie = movieRepository.getMovieForMovieId(show.getMovieId());
                if(movie != null) {
                    if(movieIdSet.add(movie.getId())) {
                        movieResponse = new MovieResponse();

                        movieResponse.setMovieName(movie.getMovieName());
                        movieResponse.setGenreType(movie.getGenreType());
                        movieResponse.setImageUrl(movie.getImageUrl());

                        movieResponseList.add(movieResponse);
                    }
                }
            }
        }
        return  new ResponseEntity<>(movieResponseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ActorResponse>> actorList(){
        ActorResponse actorResponse = null;
        List<ActorResponse> actorResponseList = new ArrayList<>();

        List<Actor> actorList = actorRepository.getActorList();
        for(Actor actor : actorList) {
            actorResponse = new ActorResponse();
            actorResponse.setActor(actor.getActorName());

            actorResponseList.add(actorResponse);
        }

        return  new ResponseEntity<>(actorResponseList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<MovieDetailResponse> movieDetails(MovieRequest movieRequest) {
            MovieDetailResponse movieDetailResponse = new MovieDetailResponse();

            Movie movie = movieRepository.getMovieForMovieName(movieRequest.getKey());

            Actor actor = actorRepository.getActorForName(movie.getActorId());
            //Cinema cinema = cinemaRepository.getCinemaForMovieId(movie.getId());

            //Rate rate = rateRepository.getRateForCinema(movie.getId());
            //System.out.println(rate);

            movieDetailResponse.setMovieName(movie.getMovieName());
            movieDetailResponse.setLanguage(movie.getLanguage());
            movieDetailResponse.setDescription(movie.getDescription());
            movieDetailResponse.setGenreType(movie.getGenreType());
            movieDetailResponse.setDuration(movie.getDuration());
            movieDetailResponse.setPrice(movie.getPrice());
            movieDetailResponse.setActorName(actor.getActorName());
            //movieDetailResponse.setCinemaName(cinema.getCinemaName());
            //movieDetailResponse.setCinemaAddress(cinema.getCinemaAddress());
            //movieDetailResponse.setTotalCinemaHall(cinema.getTotalCinemaHall());
            //movieDetailResponse.setRate(rate.getRate());

        return new ResponseEntity<>(movieDetailResponse,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<CinemaHallResponse>> cinemaHallDetail(String cinemaName) {
        List<CinemaHallResponse> cinemaHallResponseList = new ArrayList<>();
        CinemaHallResponse cinemaHallResponse = null;
        Cinema cinema = cinemaRepository.getCinemaForCinemaName(cinemaName);
        List<CinemaHall> cinemaHallList = cinemaHallRepository.getAllCinemaHallForCinemaId(cinema.getId());

        if(cinemaHallList.size() != 0) {
            for (CinemaHall cinemaHall : cinemaHallList) {
                if(showRepository.existsByCinemaHallId(cinemaHall.getId())) {
                    cinemaHallResponse = new CinemaHallResponse();

                    cinemaHallResponse.setCinemaHallName(cinemaHall.getCinemaHallName());
                    cinemaHallResponse.setCinemaId(cinemaHall.getCinemaId());
                    cinemaHallResponse.setTotalSeat(cinemaHall.getTotalSeat());

                    cinemaHallResponseList.add(cinemaHallResponse);
                }
            }
        }
        return new ResponseEntity<>(cinemaHallResponseList,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CinemaHallSeatResponse>> seatDetail(String cinemaName, String cinemaHallName) {
        List<CinemaHallSeatResponse> seatResponsesList = new ArrayList<>();
        CinemaHallSeatResponse cinemaHallSeatResponse = null;
        Cinema cinema = cinemaRepository.getCinemaForCinemaName(cinemaName);
        CinemaHall cinemaHall = cinemaHallRepository.findCinemaHallByCinemaIdAndCinemaHallName(cinema.getId(), cinemaHallName);
        List<Seat> seatList = cinemaHallSeatRepository.getDetailForSeat(cinemaHall.getId());

        for(Seat seat : seatList) {
            cinemaHallSeatResponse = new CinemaHallSeatResponse();
            cinemaHallSeatResponse.setSeatNo(seat.getSeatNo());
            cinemaHallSeatResponse.setSeatType(seat.getSeatType());
            cinemaHallSeatResponse.setColumn(seat.getColomn());
            cinemaHallSeatResponse.setRow(seat.getRow());
            cinemaHallSeatResponse.setPrice(seat.getPriceId());
            cinemaHallSeatResponse.setSeatId(seat.getId());
            seatResponsesList.add(cinemaHallSeatResponse);
        }

        return new ResponseEntity<>(seatResponsesList,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CinemaResponse>> cinemaList(String movieName) {
        List<CinemaResponse> cinemaResponsesList = new ArrayList<>();
        CinemaResponse cinemaResponse = null;
        Movie movie = movieRepository.getMovieForMovieName(movieName);
        //List<Show> showList = showRepository.getShowIdForMovieId(movie.getId());
        //for(Show show : showList) {
            //CinemaHall cinemaHall = cinemaHallRepository.getHallForCinemaHallId(show.getCinemaHallId());
            List<Cinema> cinemaList = cinemaRepository.getCinemaForMovieId(movie.getId());
            for(Cinema cinema : cinemaList) {
                cinemaResponse = new CinemaResponse();
                cinemaResponse.setCinemaName(cinema.getCinemaName());
                cinemaResponse.setCinemaAddress(cinema.getCinemaAddress());
                cinemaResponse.setTotalCinemaHall(cinema.getTotalCinemaHall());
                cinemaResponsesList.add(cinemaResponse);
            }
        return new ResponseEntity<>(cinemaResponsesList,HttpStatus.OK);
    }


}

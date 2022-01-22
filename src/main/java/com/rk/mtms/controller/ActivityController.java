package com.rk.mtms.controller;

import com.rk.mtms.request.*;
import com.rk.mtms.response.BookingDetailResponse;
import com.rk.mtms.response.PaymentResponse;
import com.rk.mtms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    //user activity
    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Boolean>> doBooking(@RequestBody BookingRequest bookingRequest) {
        return activityService.movieBooking(bookingRequest);
    }

    @RequestMapping(value = "/payment",method = RequestMethod.POST)
    public ResponseEntity<PaymentResponse> doPayment(@RequestBody PaymentRequest paymentRequest) {
        return activityService.moviePayment(paymentRequest);
    }

    @RequestMapping(value = "/bookingDetail",method = RequestMethod.POST)
    public  ResponseEntity<BookingDetailResponse> getBookingDetail(@RequestBody BookingDetailRequest bookingDetailRequest) {
        return activityService.bookingDetail(bookingDetailRequest);
    }

    @RequestMapping(value = "/deleteBooking/{userName}",method = RequestMethod.DELETE)
    public ResponseEntity<Map<String,Boolean>> DeleteBooking(@PathVariable("userName") String userName) {
        return activityService.deleteBookingDetail(userName);
    }

    // manager activity
    @RequestMapping(value = "/addMovie",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Boolean>> addMovies(@RequestBody AddMovieRequest addMovieRequest) {
        return activityService.addMovie(addMovieRequest);
    }

    @RequestMapping(value = "/addCinema",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Boolean>> addCinemas(@RequestBody AddCinemaRequest addCinemaRequest) {
        return activityService.addCinema(addCinemaRequest);
    }

    @RequestMapping(value = "/addShow",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Boolean>> addShows(@RequestBody AddShowRequest addShowRequest) {
        return activityService.addShow(addShowRequest);
    }

    @RequestMapping(value = "/addCinemaHall",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Boolean>> addCinemaHalls(@RequestBody AddCinemaHallRequest addCinemaHallRequest) {
        return activityService.addCinemaHall(addCinemaHallRequest);
    }

    @RequestMapping(value = "/deleteMovie/{movieName}",method = RequestMethod.DELETE)
    public ResponseEntity<Map<String,Boolean>> deleteMovies(@PathVariable String movieName) {
        return activityService.deleteMovie(movieName);
    }

    @RequestMapping(value = "/deleteShow/{movieName}/{cinemaHallName}",method = RequestMethod.DELETE)
    public ResponseEntity<Map<String,Boolean>> deleteShows(@PathVariable("movieName") String movieName,
                                                           @PathVariable("cinemaHallName") String cinemaHallName) {
        return activityService.deleteShow(movieName, cinemaHallName);
    }

}

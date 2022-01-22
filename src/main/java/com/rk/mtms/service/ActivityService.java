package com.rk.mtms.service;

import com.rk.mtms.request.*;
import com.rk.mtms.response.BookingDetailResponse;
import com.rk.mtms.response.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ActivityService {

    ResponseEntity<Map<String,Boolean>> movieBooking(BookingRequest bookingRequest);

    ResponseEntity<PaymentResponse> moviePayment(PaymentRequest paymentRequest);

    ResponseEntity<BookingDetailResponse> bookingDetail(BookingDetailRequest bookingDetailRequest);

    ResponseEntity<Map<String,Boolean>> deleteBookingDetail(String userName);

    ResponseEntity<Map<String,Boolean>> addMovie(AddMovieRequest addMovieRequest);

    ResponseEntity<Map<String, Boolean>> addCinema(AddCinemaRequest addCinemaRequest);

    ResponseEntity<Map<String, Boolean>> addShow(AddShowRequest addShowRequest);

    ResponseEntity<Map<String, Boolean>> addCinemaHall(AddCinemaHallRequest addCinemaHallRequest);

    ResponseEntity<Map<String, Boolean>> deleteMovie(String movieName);

    ResponseEntity<Map<String, Boolean>> deleteShow(String movieName, String cinemaHallName);
}

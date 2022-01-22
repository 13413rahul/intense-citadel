package com.rk.mtms.serviceImpl;

import com.rk.mtms.entity.*;
import com.rk.mtms.enums.BookingType;
import com.rk.mtms.enums.Status;
import com.rk.mtms.notification.EmailNotification;
import com.rk.mtms.repository.*;
import com.rk.mtms.request.*;
import com.rk.mtms.response.BookingDetailResponse;
import com.rk.mtms.response.PaymentResponse;
import com.rk.mtms.service.ActivityService;
import com.rk.mtms.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    CinemaHallRepository cinemaHallRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    RateRepository rateRepository;
    @Autowired
    SeatService seatService;
    @Autowired
    EmailNotification emailNotification;

    @Override
    public ResponseEntity<Map<String,Boolean>> movieBooking(BookingRequest bookingRequest) {
        Map<String,Boolean> resultMap = new HashMap<>();

        User user = userRepository.getUserForUserName(bookingRequest.getUserName(),"Student");

        Movie movie = movieRepository.getMovieForMovieName(bookingRequest.getMovieName());

//        Cinema cinema = cinemaRepository.getCinemaForCinemaName(bookingRequest.getCinemaName());
//
//        CinemaHall cinemaHall = cinemaHallRepository.getHallForCinemaHallName(bookingRequest.getCinemaHallName());

        List<Show> show = showRepository.getShowIdForMovieId(movie.getId());

        bookingRepository.insertIntoBookingTable(bookingRequest.getAmount(),Arrays.toString(bookingRequest.getSeatList().toArray(new Integer[0])),show.get(0).getId(), Status.PENDING.toString(),user.getId(),5, BookingType.FIRST_CLASS.toString());
        for(int seatList : bookingRequest.getSeatList()) {
            seatService.updateSeat(seatList,"booked");
        }
        resultMap.put("isSuccessMovieBooked",true);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PaymentResponse> moviePayment(PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        Random random = new Random();
        int transactionId = random.nextInt(100000);
        paymentRepository.insertIntoPaymentTable(paymentRequest.getAmount(), "pass",paymentRequest.getPaymentType(), transactionId);

        Payment payment = paymentRepository.getPaymentForTransactionId(transactionId);
        User user = userRepository.getUserForUserName(paymentRequest.getUserName(),"Student");
        Booking booking = bookingRepository.getBookingForUserId(user.getId());
        Show show = showRepository.getShowForShowId(booking.getShowId());
        Movie movie = movieRepository.getMovieForMovieId(show.getMovieId());
        CinemaHall cinemaHall = cinemaHallRepository.getHallForCinemaHallId(show.getCinemaHallId());
        Cinema cinema = cinemaRepository.getCinemaForCinemaId(cinemaHall.getCinemaId());
        receiptRepository.insertIntoReceiptTable(payment.getId(), payment.getAmount(), 0, Status.BOOKED.toString());

        Receipt receipt = receiptRepository.getPaymentForPaymentId(payment.getId());
        bookingRepository.updateBookingTable(receipt.getId(), Status.BOOKED.toString(), user.getId());

        paymentResponse.setUserName(paymentRequest.getUserName());
        paymentResponse.setMovieName(movie.getMovieName());
        paymentResponse.setCinemaName(cinema.getCinemaName());
        paymentResponse.setCinemaHAllName(cinemaHall.getCinemaHallName());
        paymentResponse.setPaymentType(payment.getPaymentType());
        paymentResponse.setSeatList(booking.getSeatList());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setDuration(movie.getDuration());
        paymentResponse.setTransactionId(transactionId);
        paymentResponse.setTypeBooked(receipt.getTypeBooked());

        String emailBody = "";
        emailBody = emailBody +
                "User Name: " + user.getUserName() + "\n"+
                "Movie Name: " + movie.getMovieName() + "\n" +
                "Cinema Name: " +cinema.getCinemaName() + "---" + cinema.getCinemaAddress() + "\n" +
                "CinemaHall Name: " + cinemaHall.getCinemaHallName() + "\n" +
                "Amount: " + booking.getAmount() + "\n" +
                "Duration: " + movie.getDuration() + "\n"+
                "Seat Number: " + booking.getSeatList() + "\n"+
                "Booking Type: " + booking.getTypeBooked() + "\n"+
                "Payment Type: " + payment.getPaymentType() + "\n"+
                "Transaction Id: " + transactionId + "\n" +
                "Number of Tickets: " + booking.getSeatList().length() + "\n";

//                "Date: " + booked.getDate() + "\n" +
//                "Time: " + booked.getTime() + "\n" +
//                "Total Price: " + booked.getTotalPrice() + "\n" +
//                "Discount: " + booked.getDiscount() + "\n" +
//                "Final Price: " + booked.getFinalPrice() + "\n";

        emailNotification.sendEmailNotification(user.getEmail(), emailBody);

        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingDetailResponse> bookingDetail(BookingDetailRequest bookingDetailRequest) {
        BookingDetailResponse bookingDetailResponse = new BookingDetailResponse();
        User user = userRepository.getUserForUserName(bookingDetailRequest.getUserName(),"Student");
        Booking booking = bookingRepository.getBookingForUserId(user.getId());
        Show show = showRepository.getShowForShowId(booking.getShowId());
        Movie movie = movieRepository.getMovieForMovieId(show.getMovieId());
        CinemaHall cinemaHall = cinemaHallRepository.getHallForCinemaHallId(show.getCinemaHallId());
        Cinema cinema = cinemaRepository.getCinemaForCinemaId(cinemaHall.getCinemaId());

        bookingDetailResponse.setUserName(bookingDetailRequest.getUserName());
        bookingDetailResponse.setMovieName(movie.getMovieName());
        bookingDetailResponse.setCinemaName(cinema.getCinemaName());
        bookingDetailResponse.setCinemaHallName(cinemaHall.getCinemaHallName());
        bookingDetailResponse.setSeatList(booking.getSeatList());
        bookingDetailResponse.setAmount(booking.getAmount());
        bookingDetailResponse.setDuration(movie.getDuration());
        bookingDetailResponse.setStartTime(show.getStartTime());
        bookingDetailResponse.setTypeBooked(booking.getTypeBooked());
        return new ResponseEntity<>(bookingDetailResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String,Boolean>> deleteBookingDetail(String userName) {
        Map<String,Boolean> resultMap = new HashMap<>();
        User user = userRepository.getUserForUserName(userName,"Student");
        bookingRepository.deleteBookingDetail(user.getId());
        resultMap.put("Deletion success",true);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String,Boolean>> addMovie(AddMovieRequest addMovieRequest) {

        Map<String,Boolean> responseMap = new HashMap<>();

        Actor actor = actorRepository.getActorForId(addMovieRequest.getActorName());
        if(actor == null) {
            actorRepository.insertActor(addMovieRequest.getActorName());
            actor = actorRepository.getActorForId(addMovieRequest.getActorName());
        }

        Rate rate = rateRepository.getRateForRate(addMovieRequest.getRate(),addMovieRequest.getRatedBy());
        if(rate == null) {
            rateRepository.insertRate(4,addMovieRequest.getRatedBy(),addMovieRequest.getRate());
            rate = rateRepository.getRateForRate(addMovieRequest.getRate(),addMovieRequest.getRatedBy());
        }

        LocalDate releasedDate = LocalDate.parse(addMovieRequest.getReleasedDateString());
        movieRepository.addMovie(addMovieRequest.getMovieName(),addMovieRequest.getLanguage(),addMovieRequest.getPrice(),
                rate.getId(),addMovieRequest.getGenreType(),addMovieRequest.getDuration(),
                addMovieRequest.getDescription(),actor.getId(),
                addMovieRequest.getReleasedDateString(),releasedDate);
        rateRepository.updateMovieId(5,rate.getId());
        responseMap.put("isSuccessMovieAdded",true);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> addCinema(AddCinemaRequest addCinemaRequest) {
        Map<String,Boolean> responseMap = new HashMap<>();
        Movie movie = movieRepository.getMovieForMovieName(addCinemaRequest.getMovieName());
        cinemaRepository.addCinema(addCinemaRequest.getCinemaName(),addCinemaRequest.getCinemaAddress(),movie.getId(),addCinemaRequest.getTotalCinemaHall());
        responseMap.put("isSuccessCinemaAdded",true);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> addShow(AddShowRequest addShowRequest) {
        Map<String,Boolean> responseMap = new HashMap<>();
        Movie movie = movieRepository.getMovieForMovieName(addShowRequest.getMovieName());
        CinemaHall cinemaHall = cinemaHallRepository.getHallForCinemaHallName(addShowRequest.getCinemaHallName());
        showRepository.addShow(movie.getId(),addShowRequest.getStartTime(), cinemaHall.getId());
        Show show = showRepository.findShowByMovieIdAndCinemaHallId(movie.getId(), cinemaHall.getId());

        Seat seat = new Seat();
        seat.setHallId(cinemaHall.getId());
        seat.setSeatType("good");
        seat.setStatus("Available");
        seat.setPriceId(3);
        seat.setShowId(show.getId());
        responseMap.put("isSuccessShowAdded",true);
        seatService.addSeat(seat, cinemaHall.getTotalSeat());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> addCinemaHall(AddCinemaHallRequest addCinemaHallRequest) {
        Map<String,Boolean> responseMap = new HashMap<>();
        Cinema cinema = cinemaRepository.getCinemaForCinemaName(addCinemaHallRequest.getCinemaName());
        cinemaHallRepository.addCinemaHall(cinema.getId(),addCinemaHallRequest.getCinemaHallName(),addCinemaHallRequest.getTotalSeat());
        responseMap.put("isSuccessCinemaHallAdded",true);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteMovie(String movieName) {
        Map<String,Boolean> responseMap = new HashMap<>();
        movieRepository.deleteMovie(movieName);
        responseMap.put("isSuccessMovieDeletion",true);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteShow(String movieName, String cinemaHallName) {
        Map<String,Boolean> responseMap = new HashMap<>();
        Movie movie = movieRepository.getMovieForMovieName(movieName);
        CinemaHall cinemaHall = cinemaHallRepository.getHallForCinemaHallName(cinemaHallName);
        Show show = showRepository.findShowByMovieIdAndCinemaHallId(movie.getId(), cinemaHall.getId());

        showRepository.deleteShow(movie.getId(), cinemaHall.getId());

        Seat seat = new Seat();
        seat.setShowId(show.getId());
        responseMap.put("isSuccessShowDeletion",true);
        seatService.deleteShow(seat, cinemaHall.getTotalSeat());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }


}

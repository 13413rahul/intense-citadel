package com.rk.mtms.response;

import lombok.Data;

@Data
public class PaymentResponse {
    String userName;
    String movieName;
    String cinemaName;
    String cinemaHAllName;
    String seatList;
    int amount;
    int duration;
    String paymentType;
    String typeBooked;
    int transactionId;
}

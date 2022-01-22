package com.rk.mtms.request;

import lombok.Data;

@Data
public class PaymentRequest {
    int amount;
    String paymentType;
    String userName;
}

package com.henrique.paymentservice.services;

import com.henrique.paymentservice.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}

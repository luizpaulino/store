package com.store.payment.controller;

import com.store.payment.dto.request.PaymentRequest;
import com.store.payment.dto.response.PaymentResponse;
import com.store.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public PaymentResponse executePayment(@RequestBody PaymentRequest paymentRequest) throws IOException {
        return paymentService.execute(paymentRequest);
    }
}


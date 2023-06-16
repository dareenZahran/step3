package com.project.demo.Payment;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(Long paymentId) {
	    super("Could not find product " + paymentId);
	}
}

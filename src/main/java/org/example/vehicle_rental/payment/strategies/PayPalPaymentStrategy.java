package org.example.vehicle_rental.payment.strategies;

import org.example.vehicle_rental.Price;

public class PayPalPaymentStrategy implements IPaymentStrategy {

    public boolean charge(Price price) {
        System.out.println("Paypal charge: " + price.getValue());
        return true;
    }
}

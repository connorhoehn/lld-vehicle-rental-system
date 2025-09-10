package org.example.vehicle_rental.payment.strategies;

import org.example.vehicle_rental.Price;

public class CashPaymentStrategy implements IPaymentStrategy {
    public boolean charge(Price price) {
        System.out.println("Cash charge: " + price.getValue());
        return true;
    }

}

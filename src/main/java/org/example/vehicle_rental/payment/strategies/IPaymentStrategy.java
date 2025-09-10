package org.example.vehicle_rental.payment.strategies;

import org.example.vehicle_rental.Price;

public interface IPaymentStrategy {
    public boolean charge(Price price);
}

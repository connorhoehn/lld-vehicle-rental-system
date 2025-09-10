package org.example.vehicle_rental.payment;

import java.util.Map;

import org.example.vehicle_rental.Booking;
import org.example.vehicle_rental.payment.strategies.IPaymentStrategy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentProcessor {
    Map<PaymentStrategyTypes, IPaymentStrategy> paymentStrategies;
    // Map<String, IPricingStrategy> pricingStrategies;

    public boolean processPayment(Booking booking) {
        IPaymentStrategy strategy = this.paymentStrategies.get(booking.getPaymentType());

        return strategy.charge(booking.getVehicle().getPrice());
    }
}

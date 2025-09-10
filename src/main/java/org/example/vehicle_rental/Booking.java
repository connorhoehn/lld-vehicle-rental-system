package org.example.vehicle_rental;

import org.example.vehicle_rental.payment.PaymentStrategyTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Booking {
    private String id;
    private Vehicle vehicle;
    private Range timeRange;
    private PaymentStrategyTypes paymentType;
}

package org.example.vehicle_rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Vehicle {
    private String id;
    private VehicleType type;
    private Branch branch;
    private Price price;
}

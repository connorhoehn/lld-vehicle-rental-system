package org.example.vehicle_rental;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Location {
    private String lat;
    private String lng;
    private String displayAddress;
}

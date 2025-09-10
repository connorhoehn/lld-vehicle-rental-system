package org.example.vehicle_rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Branch {
    private String id;
    private Location location;
}

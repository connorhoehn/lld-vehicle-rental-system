package org.example.vehicle_rental.search;

import org.example.vehicle_rental.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TypeItemData implements IFilterItemData {
    private VehicleType value;
}

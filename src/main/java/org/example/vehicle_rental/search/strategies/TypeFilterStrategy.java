package org.example.vehicle_rental.search.strategies;

import java.util.Map;

import org.example.vehicle_rental.Vehicle;
import org.example.vehicle_rental.search.FilterItem;
import org.example.vehicle_rental.search.FilterTypes;
import org.example.vehicle_rental.search.TypeItemData;

public class TypeFilterStrategy implements IFilterStrategy {
    public boolean isMatching(Vehicle vehicle, FilterItem item, Map<FilterTypes, IFilterStrategy> stratgies) {
        TypeItemData data = ((TypeItemData) item.getData());
        return vehicle.getType() == data.getValue();
    }

}

package org.example.vehicle_rental.search.strategies;

import java.util.Map;

import org.example.vehicle_rental.Range;
import org.example.vehicle_rental.Vehicle;
import org.example.vehicle_rental.search.FilterItem;
import org.example.vehicle_rental.search.FilterTypes;
import org.example.vehicle_rental.search.RangeFilterItemData;

public class PriceFilterStrategy implements IFilterStrategy {
    public boolean isMatching(Vehicle vehicle, FilterItem item, Map<FilterTypes, IFilterStrategy> stratgies) {
        
        Range rangeData = ((RangeFilterItemData) item.getData()).getValue();
        
        return rangeData.getStart() <= vehicle.getPrice().getValue() && vehicle.getPrice().getValue()  <= rangeData.getEnd();
    }

}

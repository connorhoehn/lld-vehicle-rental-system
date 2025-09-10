package org.example.vehicle_rental.search.strategies;

import java.util.Map;

import org.example.vehicle_rental.Vehicle;
import org.example.vehicle_rental.search.FilterItem;
import org.example.vehicle_rental.search.FilterTypes;

public interface IFilterStrategy {
    boolean isMatching(Vehicle vehicle, FilterItem item, Map<FilterTypes, IFilterStrategy> stratgies);
}

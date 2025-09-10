package org.example.vehicle_rental.search.strategies;

import java.util.Map;

import org.example.vehicle_rental.Vehicle;
import org.example.vehicle_rental.search.BinaryFilterItemData;
import org.example.vehicle_rental.search.FilterItem;
import org.example.vehicle_rental.search.FilterTypes;

public class AndFilterStrategy implements IFilterStrategy {
    public boolean isMatching(Vehicle vehicle, FilterItem item, Map<FilterTypes, IFilterStrategy> stratgies) {
        BinaryFilterItemData nodeData = ((BinaryFilterItemData) item.getData());

        FilterItem left = nodeData.getLeft();
        FilterItem right = nodeData.getRight();
        
        return stratgies.get(left.getName()).isMatching(vehicle, left, stratgies)
            && stratgies.get(right.getName()).isMatching(vehicle, right, stratgies);
    }
}

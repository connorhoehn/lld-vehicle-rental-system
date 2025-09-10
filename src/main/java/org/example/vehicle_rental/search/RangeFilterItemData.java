package org.example.vehicle_rental.search;

import org.example.vehicle_rental.Range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RangeFilterItemData implements IFilterItemData {
    private Range value;
}

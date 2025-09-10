package org.example.vehicle_rental.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BinaryFilterItemData implements IFilterItemData {
    private FilterItem left;
    private FilterItem right;
}

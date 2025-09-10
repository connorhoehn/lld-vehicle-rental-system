package org.example.vehicle_rental.search;

import lombok.*;

@AllArgsConstructor
@Getter
public class FilterItem {
    private FilterTypes name;
    private IFilterItemData data;
}

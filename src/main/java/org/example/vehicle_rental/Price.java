package org.example.vehicle_rental;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Price {
    private int value;
    private Currency currency;

    public int compare(Price price) {
        if (this.currency != price.currency) {
            throw new RuntimeException("Incomparable currencies");
        }
        return value - price.value;
    }
}

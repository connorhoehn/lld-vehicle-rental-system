package org.example.vehicle_rental;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Range {

    private long start;
    private long end;

    public boolean doesOverlap(Range range) {
        return this.start < range.getEnd() &&
                this.end > range.getStart();
    }
}

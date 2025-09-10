
# LLD / Machine Coding: Vehicle Rental System

## Snippet
```java
...
        List<Vehicle> vehicles2 = vehicleRentalService.viewInventory(VehicleType.HATCH_BACK, new Range(0, 0));
        System.out.println(vehicles2.size());

        FilterItem searchFilters = new FilterItem(
            FilterTypes.AND,
            new BinaryFilterItemData(
                new FilterItem(
                    FilterTypes.PRICE,
                    new RangeFilterItemData(new Range(150,152))
                ),
                new FilterItem(
                    FilterTypes.TYPE,
                    new TypeItemData(VehicleType.SEDAN)
                )
            )
        );
```

### Core Operations
- Book vehicle
- Check booking status
- Cancel booking
- Pay for vehicle
- Return vehicle


## Planned Features & Requirements

### Filters
- Location-based filtering
- Model/Year filtering
- Ratings filtering

### Sorting
- Sort by closer locations
- Sort by better rating



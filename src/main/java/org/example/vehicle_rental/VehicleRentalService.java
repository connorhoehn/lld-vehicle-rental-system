package org.example.vehicle_rental;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

import org.example.vehicle_rental.payment.PaymentProcessor;
import org.example.vehicle_rental.payment.PaymentStrategyTypes;
import org.example.vehicle_rental.payment.strategies.IPaymentStrategy;
import org.example.vehicle_rental.search.FilterItem;
import org.example.vehicle_rental.search.FilterTypes;
import org.example.vehicle_rental.search.strategies.IFilterStrategy;

@AllArgsConstructor
public class VehicleRentalService {

    private List<Vehicle> allVehicles;
    @Getter
    private List<Booking> bookings;

    private Map<FilterTypes, IFilterStrategy> filterStrategies;
    private PaymentProcessor paymentProcessor;

    public boolean cancelBooking(Booking booking) {

        List<Booking> allBookings = new ArrayList<>();
        Boolean found = false;

        for (Booking b: bookings) {
            if (b.getId() == booking.getId()) {
                found = true;
                continue;
            }
            allBookings.add(b);
        }

        this.bookings = allBookings;

        return found;
    }

    public List<Vehicle> search(FilterItem rootFilterItem) {

        FilterTypes type = rootFilterItem.getName();

        //Get Bookings
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle vehicle : allVehicles) {
            IFilterStrategy strategy = filterStrategies.get(type);
            if (strategy.isMatching(vehicle, rootFilterItem, filterStrategies)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    public List<Vehicle> viewInventory(VehicleType type, Range timeRange) {

        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle vehicle : allVehicles) {
            boolean isMatched = false;

            if (vehicle.getType() != type) {
                continue;
            }

            // find vehicle bookings and filter them out if they are booked
            for (Booking booking : bookings) {
                if (booking.getVehicle().getId().equals(vehicle.getId())
                        && booking.getTimeRange().doesOverlap(timeRange)) {
                    isMatched = true;
                }
            }

            if (!isMatched) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    // Define return type and
    public Booking bookVehicle(VehicleType vehicleType, Range timeRange, PaymentStrategyTypes type) {
        // all vehicles

        List<Vehicle> matchingTypeVehicels = new ArrayList<>();
        for (Vehicle vehicle : allVehicles) {
            boolean isAvailable = true;
            // filter by given vehicleType
            if (!vehicle.getType().equals(vehicleType)) {
                isAvailable = false;
            }

            // find bookings for those vehicles and filter those which are already booked in that time frame.
            for (Booking booking : bookings) {
                if (booking.getVehicle().getId().equals(vehicle.getId())
                        && booking.getTimeRange().doesOverlap(timeRange)) {
                    isAvailable = false;
                }
            }

            if (isAvailable) {
                matchingTypeVehicels.add(vehicle);
            }
        }

        if (matchingTypeVehicels.isEmpty()) {
            throw new RuntimeException("No vehicle is available for rent in the given time slot");
        }

        // Sort by price - reverse order
        Collections.sort(matchingTypeVehicels, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.getPrice().compare(o2.getPrice());
            }
        });
        // create the booking with the first one.
        final Booking newBooking = new Booking(UUID.randomUUID().toString(), matchingTypeVehicels.getFirst(), timeRange, type);
        
        if (!paymentProcessor.processPayment(newBooking)) {
            return null;
        }
        
        bookings.add(newBooking);

        // return the created booking
        return newBooking;
    }
}

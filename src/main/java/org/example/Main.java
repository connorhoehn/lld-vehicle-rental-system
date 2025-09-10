package org.example;

import org.example.vehicle_rental.*;
import org.example.vehicle_rental.payment.PaymentProcessor;
import org.example.vehicle_rental.payment.PaymentStrategyTypes;
import org.example.vehicle_rental.payment.strategies.CashPaymentStrategy;
import org.example.vehicle_rental.payment.strategies.PayPalPaymentStrategy;
import org.example.vehicle_rental.search.BinaryFilterItemData;
import org.example.vehicle_rental.search.FilterItem;
import org.example.vehicle_rental.search.FilterTypes;
import org.example.vehicle_rental.search.RangeFilterItemData;
import org.example.vehicle_rental.search.TypeItemData;
import org.example.vehicle_rental.search.strategies.AndFilterStrategy;
import org.example.vehicle_rental.search.strategies.PriceFilterStrategy;
import org.example.vehicle_rental.search.strategies.TypeFilterStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        final ArrayList<Vehicle> allVehicles = new ArrayList<>();

        final Branch branch1 = new Branch("branch1", new Location("1", "1", "California"));
        allVehicles.add(new Vehicle("vehicle1", VehicleType.SEDAN, branch1, new Price(100, Currency.USD)));
        allVehicles.add(new Vehicle("vehicle2", VehicleType.SEDAN, branch1, new Price(110, Currency.USD)));
        allVehicles.add(new Vehicle("vehicle3", VehicleType.SEDAN, branch1, new Price(100, Currency.USD)));
        allVehicles.add(new Vehicle("vehicle4", VehicleType.HATCH_BACK, branch1, new Price(100, Currency.USD)));

        final Branch branch2 = new Branch("branch2", new Location("1", "1", "New york"));
        allVehicles.add(new Vehicle("vehicle5", VehicleType.SEDAN,   branch2, new Price(150, Currency.USD)));
        allVehicles.add(new Vehicle("vehicle6", VehicleType.HATCH_BACK, branch2, new Price(90, Currency.USD)));

        final VehicleRentalService vehicleRentalService = new VehicleRentalService(
            allVehicles,
            new ArrayList<>(),
            Map.of(
                FilterTypes.AND, new AndFilterStrategy(),
                FilterTypes.PRICE, new PriceFilterStrategy(),
                FilterTypes.TYPE, new TypeFilterStrategy()
            ),
            new PaymentProcessor(Map.of(
                PaymentStrategyTypes.CASH, new CashPaymentStrategy(),
                PaymentStrategyTypes.PAYPAL, new PayPalPaymentStrategy()
        )));

        // this should return the booked vehicle.
        final Booking booking1 = vehicleRentalService.bookVehicle(VehicleType.SEDAN, new Range(0, 5), PaymentStrategyTypes.PAYPAL);
        System.out.println(booking1);

        final Booking booking2 = vehicleRentalService.bookVehicle(VehicleType.SEDAN, new Range(4, 5), PaymentStrategyTypes.CASH);
        System.out.println(booking2);

        final Booking booking3 = vehicleRentalService.bookVehicle(VehicleType.SEDAN, new Range(3, 5), PaymentStrategyTypes.PAYPAL);
        System.out.println(booking3);

        final Booking booking4 = vehicleRentalService.bookVehicle(VehicleType.HATCH_BACK, new Range(4, 5), PaymentStrategyTypes.CASH);
        System.out.println(booking4);

        List<Vehicle> vehicles = vehicleRentalService.viewInventory(VehicleType.SEDAN, new Range(0, 0));
        System.out.println(vehicles.size());

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

        List<Vehicle> filteredVehicles = vehicleRentalService.search(searchFilters);
        System.out.println("Number of searched vehicles returned: " + filteredVehicles.size());

        System.out.println("Current amount of bookings: " + vehicleRentalService.getBookings().size());
        vehicleRentalService.cancelBooking(booking3);
        System.out.println("Current amount of booking after cancelation: " + vehicleRentalService.getBookings().size());
    }
}   
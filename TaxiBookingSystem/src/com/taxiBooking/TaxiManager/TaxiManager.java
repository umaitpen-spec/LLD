package com.taxiBooking.TaxiManager;

//import com.taxiBooking.TaxiManager;
import com.taxiBooking.BookingManager.*;
import static com.taxiBooking.Main.*;

import java.util.List;

public class TaxiManager {

    public void displayTaxi() {
        System.out.println("Taxi No:     Total Earning:");
        System.out.format("%8s %8s %8s %8s %8s %8s %8s \n",
                "BookingId",
                "CustomerId",
                "From",
                "To",
                "PickUpTime",
                "DropTime",
                "Amount");
        for (Taxi taxi : taxiList) {
            System.out.println("Taxi-" + taxi.getTaxiId() + " Total Earnings:" + taxi.getTotEarning());
            for (Booking booking : taxi.getBookingList()) {
                System.out.println("Taxi details:" + booking.getCustId());
                System.out.printf("%8d %8d %8s %8s %8d %8d %8s \n",
                        booking.getBookingId(),
                        booking.getCustId(),
                        booking.getPickUpPoint(),
                        booking.getDropPoint(),
                        booking.getPickUpTime(),
                        booking.getDropTime(),
                        booking.getAmount()
                );
            }
        }
    }

    public boolean isAvailable(int pickUpTime,Taxi taxi) {

        for (Booking booking : taxi.getBookingList()) {
            if (pickUpTime >= booking.getPickUpTime() && pickUpTime < booking.getDropTime())
                return false;
        }
        return true;
    }

    public void addBookingToTaxi(Booking booking,Taxi taxi) {
        taxi.getBookingList().add(booking);
        taxi.setTotEarning(booking.getAmount());
        taxi.setCurrentLocation(booking.getPickUpPoint());
    }
}

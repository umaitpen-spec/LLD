package com.taxiBooking.TaxiManager;

import com.taxiBooking.BookingManager.Booking;
import java.util.ArrayList;
import java.util.List;

public class Taxi {
    private int taxiId;
    private double totEarning;
    private char currentLocation = 'A';
    private TaxiStatus status = TaxiStatus.AVAILABLE;
    public List<Booking> bookingList = new ArrayList<>();

    public Taxi(int id) {
        this.taxiId = id;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public double getTotEarning() {
        return totEarning;
    }

    public char getLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(char currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public void setTotEarning(double totEarning) {
        this.totEarning += totEarning;
    }
}

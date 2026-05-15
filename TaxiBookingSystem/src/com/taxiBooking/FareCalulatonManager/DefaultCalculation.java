package com.taxiBooking.FareCalulatonManager;

public class DefaultCalculation implements FareCalculation {
    @Override
    public double calculateFare(int hr) {
        final double baseFare = 100;
        double totFare = baseFare;
        if(hr > 5)
        {
            hr -= 5;
            totFare += hr * 15;
        }
        return totFare;
    }
}

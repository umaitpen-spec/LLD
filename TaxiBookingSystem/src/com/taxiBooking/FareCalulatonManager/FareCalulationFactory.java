package com.taxiBooking.FareCalulatonManager;

public class FareCalulationFactory
{
    public static FareCalculation getFareCalculationType(String calType)
    {
        switch(calType)
        {
            case "weekdays": return new WeekDayFareCalculation();
            default:return new DefaultCalculation();
        }
    }
}
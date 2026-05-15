package com.taxiBooking.BookingManager;

import com.taxiBooking.FareCalulatonManager.DefaultCalculation;
import com.taxiBooking.FareCalulatonManager.FareCalculation;
import com.taxiBooking.FareCalulatonManager.FareCalulationFactory;
import com.taxiBooking.TaxiManager.*;
import static com.taxiBooking.Main.*;

public class BookingManager {
    TaxiManager taxiManager = new TaxiManager();
    public void makeBooking(String fareCalType)
    {
        System.out.print("Enter Customer Id:");
        int custId = sc.nextInt();
        System.out.print("Enter the PickUp Point:");
        char pickUp = sc.next().charAt(0);
        System.out.print("Enter the Drop Point:");
        char drop = sc.next().charAt(0);
        System.out.print("Enter the PickUp Time:");
        int pickTime = sc.nextInt();
        int hrs = Math.abs(drop - pickUp);
        int dropTime = pickTime + hrs;
        FareCalculation cal = FareCalulationFactory.getFareCalculationType(fareCalType);
        double amount = cal.calculateFare(hrs) ;
        Taxi selectedTaxi = null;
        int minDistance = Integer.MAX_VALUE;
        for(Taxi taxis: taxiList)
            if(taxiManager.isAvailable(pickTime,taxis))
            {
                int distance = Math.abs(taxis.getLocation() - pickUp);
                if(distance < minDistance) {
                    selectedTaxi = taxis;
                    minDistance = distance;
                }
                else if(distance == minDistance)
                {
                    if(selectedTaxi.getTotEarning() > taxis.getTotEarning())
                        selectedTaxi = taxis;
                }
            }

        if(selectedTaxi == null)
        {
            System.out.println("Taxi cannot be Alloted");
            System.out.println("Rejected");
        }
        else {
            int bookingId = selectedTaxi.getBookingList().size();
            Booking booking = new Booking(bookingId,
                    custId, pickUp, drop, pickTime, dropTime,
                    selectedTaxi.getTaxiId(), amount);
            taxiManager.addBookingToTaxi(booking,selectedTaxi);
            System.out.println("Taxi can be alloted");
            System.out.println("Taxi -" + selectedTaxi.getTaxiId()+ " is alloted");
        }
    }

}
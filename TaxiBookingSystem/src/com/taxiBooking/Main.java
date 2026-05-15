package com.taxiBooking;

import com.taxiBooking.BookingManager.BookingManager;
import com.taxiBooking.TaxiManager.Taxi;
import com.taxiBooking.TaxiManager.TaxiManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.taxiBooking.BookingManager.BookingManager;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static List<Taxi> taxiList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("Enter the total of Taxis:");
        int n = sc.nextInt();
        BookingManager bookingManager = new BookingManager();
        TaxiManager taxiManager = new TaxiManager();
        initializeTaxis(n);

        while (true) {
            System.out.println("1.Call Taxi Booking");
            System.out.println("2.Display the Taxi Details.");
            int m = sc.nextInt();
            if (m == 1)
                bookingManager.makeBooking("test");
            else if (m == 2)
                taxiManager.displayTaxi(); // No need to pass taxiList
            else
                System.exit(0);
        }
    }

    public static void initializeTaxis(int n) {
        for (int i = 1; i <= n; i++)
            taxiList.add(new Taxi(i));
    }

}
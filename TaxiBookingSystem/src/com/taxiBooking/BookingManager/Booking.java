package com.taxiBooking.BookingManager;

public class Booking {
    private int bookingId = 1;
    private int custId;
    private int taxiId;
    private int pickUpTime;
    private int dropTime;
    private char pickUpPoint;
    private char dropPoint;
    private double amount;

    public Booking(int bookingId,int custId,char pickUpPoint,char dropPoint
            ,int pickTime,int dropTime,int taxiId,double amount) {
        this.custId = custId;
        this.pickUpPoint = pickUpPoint;
        this.dropPoint = dropPoint;
        this.pickUpTime = pickTime;
        this.dropTime = dropTime;
        this.taxiId = taxiId;
        this.amount = amount;
        this.bookingId =  bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getCustId() {
        return custId;
    }

    public char getPickUpPoint() {
        return pickUpPoint;
    }public char getDropPoint() {
        return dropPoint;
    }

    public double getAmount() {
        return amount;
    }

    public int getPickUpTime() {
        return pickUpTime;
    }

    public int getDropTime() {
        return dropTime;
    }
}

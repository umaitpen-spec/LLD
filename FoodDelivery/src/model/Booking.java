package model;

import java.time.LocalDate;
import java.time.LocalTime;

import enumeration.orderStatus;

public class Booking {
    private int bookingId;
    private int customerId;
    private Location destinationPt;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private double totCost;
    private orderStatus status;

    private static int booking = 1000;

    public Booking( int customerId, Location destinationPt, LocalDate orderDate, LocalTime orderTime,
            double totCost, orderStatus status) {
        this.bookingId = booking++;
        this.customerId = customerId;
        this.destinationPt = destinationPt;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totCost = totCost;
        this.status = status;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public Location getDestinationPt() {
        return destinationPt;
    }
    public void setDestinationPt(Location destinationPt) {
        this.destinationPt = destinationPt;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public LocalTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }
    public double getTotCost() {
        return totCost;
    }
    public void setTotCost(double totCost) {
        this.totCost = totCost;
    }
    public orderStatus getStatus() {
        return status;
    }
    public void setStatus(orderStatus status) {
        this.status = status;
    }
    
}

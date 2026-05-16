package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerId;
    private String custName;
    private Location location;
    private List<Booking> orderList;

    private static int customer = 2000;

    public Customer(String custName, Location location) {
        this.customerId = customer++;
        this.custName = custName;
        this.location = location;
        this.orderList = new ArrayList<>();
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public List<Booking> getOrderList() {
        return orderList;
    }
    public void setOrderList(List<Booking> orderList) {
        this.orderList = orderList;
    }

    
}

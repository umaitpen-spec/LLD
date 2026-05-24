package model;

public class CabHistory {
    private Location sLocation;
    private Location dLocation;
    private Customer customer;
    private Driver driver;
    private int commision;
    private int totFare;

    
    public CabHistory(Location sLocation, Location dLocation, Customer customer, Driver driver, int commision,
            int totFare) {
        this.sLocation = sLocation;
        this.dLocation = dLocation;
        this.customer = customer;
        this.driver = driver;
        this.commision = commision;
        this.totFare = totFare;
    }
    public Location getsLocation() {
        return sLocation;
    }
    public void setsLocation(Location sLocation) {
        this.sLocation = sLocation;
    }
    public Location getdLocation() {
        return dLocation;
    }
    public void setdLocation(Location dLocation) {
        this.dLocation = dLocation;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public int getCommision() {
        return commision;
    }
    public void setCommision(int commision) {
        this.commision = commision;
    }
    public int getTotFare() {
        return totFare;
    }
    public void setTotFare(int totFare) {
        this.totFare = totFare;
    }
    
}

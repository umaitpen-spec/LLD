package model;

import java.util.ArrayList;
import java.util.List;

public class DeleiveryExceutive {
    private int deId;
    private String deName;
    private Location currLocation;
    private double allowance;
    private double dCharge;
    private List<Booking> deliveryList;

    private static int devID = 3000;

    
    public DeleiveryExceutive(String deName, Location currLocation) {
        this.deId = devID++;
        this.deName = deName;
        this.currLocation = currLocation;
        this.allowance = 0;
        this.dCharge = 0;
        this.deliveryList = new ArrayList<>();
    }
    
    public int getDeId() {
        return deId;
    }

    public void setDeId(int deId) {
        this.deId = deId;
    }

    public String getDeName() {
        return deName;
    }
    public void setDeName(String deName) {
        this.deName = deName;
    }
    public Location getCurrLocation() {
        return currLocation;
    }
    public void setCurrLocation(Location currLocation) {
        this.currLocation = currLocation;
    }
    public double getAllowance() {
        return allowance;
    }
    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }
    public double getdCharge() {
        return dCharge;
    }
    public void setdCharge(double dCharge) {
        this.dCharge = dCharge;
    }
    public List<Booking> getDeliveryList() {
        return deliveryList;
    }
    public void setDeliveryList(List<Booking> deliveryList) {
        this.deliveryList = deliveryList;
    }

    
}

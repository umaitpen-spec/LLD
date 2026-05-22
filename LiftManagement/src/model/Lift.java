package model;

import java.util.List;

public class Lift {
    private static int lift = 1;
    private int liftId;
    private String liftName;
    private Floor currFloor;
    private List<Floor> floorList;
    private boolean isForward;
    private int capacity;

    
    public Lift(String liftName, Floor currFloor, int capacity) {
        this.liftId = lift++;
        this.liftName = liftName;
        this.currFloor = currFloor;
        this.capacity = capacity;
    }
    public int getLift() {
        return lift;
    }
    public void setLift(int lift) {
        this.lift = lift;
    }
    public int getLiftId() {
        return liftId;
    }
    public void setLiftId(int liftId) {
        this.liftId = liftId;
    }
    public String getLiftName() {
        return liftName;
    }
    public void setLiftName(String liftName) {
        this.liftName = liftName;
    }
    public Floor getCurrFloor() {
        return currFloor;
    }
    public void setCurrFloor(Floor currFloor) {
        this.currFloor = currFloor;
    }
    public List<Floor> getFloorList() {
        return floorList;
    }
    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }
    public boolean isForward() {
        return isForward;
    }
    public void setForward(boolean isForward) {
        this.isForward = isForward;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public String toString() {
        return "LiftName=" + liftName + ", currFloor=" + currFloor.getFloorName();
    }    
}

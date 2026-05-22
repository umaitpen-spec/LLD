package model;

public class Floor {
    private int floorId;
    private String floorName;
    private static int floor = 0;

    
    public Floor(String floorName) {
        this.floorId = floor++;
        this.floorName = floorName;
    }
    public int getFloorId() {
        return floorId;
    }
    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
    public String getFloorName() {
        return floorName;
    }
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    @Override
    public String toString() {
        return "Floor [floorId=" + floorId + ", floorName=" + floorName + "]";
    }
    
}

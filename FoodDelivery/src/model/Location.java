package model;

public class Location {
    private int locationId;
    private String LocationName;

    private static int location = 4000;
    public Location(String LocationName) {
        this.LocationName = LocationName;
        this.locationId = location++;
    }


    public int getLocationId() {
        return locationId;
    }
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public String getLocationName() {
        return LocationName;
    }
    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    
}   

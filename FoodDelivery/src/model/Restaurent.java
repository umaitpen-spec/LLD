package model;

public class Restaurent {
    private int restaurentId;
    private String restaurentName;
    private Location restLocation;
    private static int restaurent = 5000;

    public Location getRestLocation() {
        return restLocation;
    }
    public void setRestLocation(Location restLocation) {
        this.restLocation = restLocation;
    }
    public Restaurent(String restaurentName, Location restLocation) {
        this.restaurentId = restaurent++;
        this.restaurentName = restaurentName;
        this.restLocation = restLocation;
    }

    public int getRestaurentId() {
        return restaurentId;
    }
    public void setRestaurentId(int restaurentId) {
        this.restaurentId = restaurentId;
    }
    public String getRestaurentName() {
        return restaurentName;
    }
    public void setRestaurentName(String restaurentName) {
        this.restaurentName = restaurentName;
    }

    
}

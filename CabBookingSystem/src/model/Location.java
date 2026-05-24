package model;

public class Location {
    private int locId;
    private String name;
    private int distFromOrigin;
    public Location(int locId, String name, int distFromOrigin) {
        this.locId = locId;
        this.name = name;
        this.distFromOrigin = distFromOrigin;
    }
    public int getLocId() {
        return locId;
    }
    public void setLocId(int locId) {
        this.locId = locId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDistFromOrigin() {
        return distFromOrigin;
    }
    public void setDistFromOrigin(int distFromOrigin) {
        this.distFromOrigin = distFromOrigin;
    }    
}

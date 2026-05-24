package model;

public class Driver {
    private int driverId;
    private int cabId;
    private Location currLocation;
    private String name;
    private String password;
    private int pass;
    private int age;
    private static int driver = 1;
    public Driver(int driverId, String name,String password, int pass, int age,int cabId,Location currLoc) {
        this.driverId = driverId;
        this.name = name;
        this.password = password;
        this.pass = pass;
        this.age = age;
        this.cabId = cabId;
        this.currLocation = currLoc;
    }
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    public int getCabId() {
        return cabId;
    }
    public void setCabId(int cabId) {
        this.cabId = cabId;
    }
    public Location getCurrLocation() {
        return currLocation;
    }
    public void setCurrLocation(Location currLocation) {
        this.currLocation = currLocation;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public int getDriverId() {
        return driverId;
    }
    public void setCustId(int driverId) {
        this.driverId = driverId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPass() {
        return pass;
    }
    public void setPass(int pass) {
        this.pass = pass;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
  
}

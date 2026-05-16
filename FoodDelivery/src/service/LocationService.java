package service;

import model.Location;
import repo.DBrepo;

public class LocationService {
    DBrepo dBrepo;
    public LocationService(DBrepo dBrepo)
    {
        this.dBrepo = dBrepo;
        start();
    }
    private void start() {
        addLocation("A");
        addLocation("B");
        addLocation("C");
        addLocation("D");
        addLocation("E");
    }

    public void addLocation(String location)
    {
        Location location1 = new Location(location);
        dBrepo.addLocation(location1);
    }

    public Location getLocationByID(int locId)
    {
        return dBrepo.getLocationById(locId);
    }
}

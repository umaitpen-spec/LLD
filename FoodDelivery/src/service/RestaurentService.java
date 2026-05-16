package service;

import model.Restaurent;
import repo.DBrepo;

public class RestaurentService {

    DBrepo dBrepo;
    LocationService locSrc;
     public RestaurentService(DBrepo dBrepo,LocationService locSrc)
    {
        this.dBrepo = dBrepo;
        this.locSrc = locSrc;
        start();
    }

    private void start() {
        addRestaurent("R1",4000);
        addRestaurent("R2",4001);
        addRestaurent("R3",4000);
        addRestaurent("R4",4001);
        addRestaurent("R5",4003);
    }
     public Restaurent getRestaurentByID(int restID) {
        return dBrepo.getRestaurentByID(restID);
    }

     public void addRestaurent(String restName,int locId)
    {
        Restaurent restaurent = new Restaurent(restName,
            locSrc.getLocationByID(locId) );
        dBrepo.addRest(restaurent);
    }
}

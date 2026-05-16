package service;

import model.DeleiveryExceutive;
import model.Location;
import repo.DBrepo;

public class DEService {
    DBrepo dBrepo;
    public DEService(DBrepo dBrepo)
    {
        this.dBrepo = dBrepo;
        start();
    }
    private void start() {
        Location loc = dBrepo.getLocationById(4000);        
        addDE("DE1",loc);
        addDE("DE2",loc);
        addDE("DE3",loc);
        addDE("DE4",loc);
        addDE("DE5",loc);
    }

    public void addDE(String name,Location location)
    {
        DeleiveryExceutive de = new DeleiveryExceutive(name,location);
        dBrepo.addDE(de);
    }

    public Location getLocationByID(int locId)
    {
        return dBrepo.getLocationById(locId);
    }

    public void displayDE() {
        System.out.println("DE Name | Delivery Charge | Allowance | Total Deliveries");
        for (DeleiveryExceutive de : dBrepo.getDeList()) {
            System.out.println(
                de.getDeName() + " | " +
                de.getdCharge() + " | " +
                de.getAllowance() + " | " +
                de.getDeliveryList().size()
            );
        }
    }
}

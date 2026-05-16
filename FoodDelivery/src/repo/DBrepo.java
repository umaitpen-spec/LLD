package repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Customer;
import model.DeleiveryExceutive;
import model.Location;
import model.Restaurent;

public class DBrepo {
    private Map<Integer,Customer> custList = new HashMap<>();
    private Map<Integer,DeleiveryExceutive> deList = new HashMap<>();
    private Map<Integer,Restaurent> restList = new HashMap<>();
    private Map<Integer,Location> locationList = new HashMap<>();
    
    public Map<Integer, Customer> getCustList() {
        return custList;
    }


    public List<DeleiveryExceutive> getDeList() {
        return new ArrayList<>(deList.values());
    }


    public Map<Integer, Restaurent> getRestList() {
        return restList;
    }


    public Map<Integer, Location> getLocationList() {
        return locationList;
    }


    public void addLocation(Location loc)
    {
        locationList.put(loc.getLocationId(),loc);
    }

    public void addCustomer(Customer cust)
    {
        custList.put(cust.getCustomerId() ,cust);
    }
    public void addRest(Restaurent rest)
    {
        restList.put(rest.getRestaurentId() ,rest);
    }
    public void addDE(DeleiveryExceutive de)
    {
        deList.put(de.getDeId() ,de);
    }

    public Location getLocationById(int locId)
    {
        return locationList.get(locId);
    }

    public Customer getCustomerByID(int custID) {
         return custList.get(custID);
    }

    public Restaurent getRestaurentByID(int restID) {
         return restList.get(restID);
    }

}

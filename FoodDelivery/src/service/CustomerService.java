package service;

import model.Customer;
import repo.DBrepo;

public class CustomerService {

    DBrepo dBrepo;
    LocationService locSrc;
    public CustomerService(DBrepo dBrepo,LocationService locSrc)
    {
        this.dBrepo = dBrepo;
        this.locSrc = locSrc;
        start();
    }
    private void start() {
        addCustomer("C1",4000);
        addCustomer("C2",4001);
        addCustomer("C3",4000);
        addCustomer("C4",4001);
        addCustomer("C5",4003);
    }

    public void addCustomer(String cust,int locId)
    {
        Customer customer1 = new Customer(cust,
            locSrc.getLocationByID(locId) );
        dBrepo.addCustomer(customer1);
    }

    public Customer getCustomerByID(int custID) {
        return dBrepo.getCustomerByID(custID);
    }
    
}

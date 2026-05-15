package repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Customer;

public class CustomerRepo
{
    private Map<Integer,Customer> custList = new HashMap<>();


    public void addCustomer(int id,Customer customer)
    {
        custList.put(id,customer);
    }

    public Customer getCustomerByID(int id)
    {
        return custList.getOrDefault(id, null);
    }

     public List<Customer> getAllCustomers()
    {
        return new ArrayList<>(custList.values());
    }


}

package service;

import Enum.Roles;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import repo.CustomerRepo;

public class CustomerService {
    CustomerRepo customerRepo;
     static int customerID = 1000;
    public CustomerService(CustomerRepo custRepo) {
        this.customerRepo = custRepo;
    }
    public Customer addCustomer(String userName, int age, String mobNo, String email, String address) {
        Customer customer = new Customer(customerID++,userName,age,mobNo,email,address,Roles.Customer);
        customerRepo.addCustomer(customer.getCustomerID(), customer);
        return customer;
    }

     public Customer getCustomerById(int custId)
    {
        return customerRepo.getCustomerByID(custId);
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepo.getAllCustomers();
    }

    public void updateAddressByID(int custId, String address) {
        Customer cust = customerRepo.getCustomerByID(custId);
        if(cust == null)
            System.out.println("Invalid Customer");
        else
        {
            cust.setAddress(address);
            System.out.println("Address Updated Successfully");
        }
    }

    public void updateNameByID(int custId, String name) {
        Customer cust = customerRepo.getCustomerByID(custId);
        if(cust == null)
            System.out.println("Invalid Customer");
        else
        {
            cust.setName(name);
            System.out.println("Name Updated Successfully");
        }
    }

    public void updateMobileNoByID(int custId, String mno) {
        Customer cust = customerRepo.getCustomerByID(custId);
        if(cust == null)
            System.out.println("Invalid Customer");
        else
        {
            cust.setMobileNumber(mno);
            System.out.println("Mobile Name Updated Successfully");
        }
    }

    public void updateAgeByID(int custId, int age) {
        Customer cust = customerRepo.getCustomerByID(custId);
        if(cust == null)
            System.out.println("Invalid Customer");
        else
        {
            cust.setAge(age);
            System.out.println("Age Updated Successfully");
        }
    }

    public List<Customer> searchByName(String name) {
        List<Customer> allCust = customerRepo.getAllCustomers();
        List<Customer> custList = new ArrayList<>();
        System.out.println(name);
        for(Customer cust:allCust)
        {

            if(cust.getName().startsWith(name))
                custList.add(cust);
        }
        return custList;
    }
}

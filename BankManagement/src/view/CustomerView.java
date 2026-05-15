package view;

import java.util.List;
import java.util.Scanner;
import model.Customer;
import service.CustomerService;
import util.Util;

public class CustomerView {
    Scanner sc;
    Customer currCust = null;
    CustomerService customerService ;
	public CustomerView(Scanner sc, CustomerService custSrc) {
		this.sc = sc;
        this.customerService = custSrc;
	}

    public void start()
    {
        System.out.println("Enter the option");
        System.out.println("1.Add Customer");
        System.out.println("2.View all Customer");
        System.out.println("3.Update Customer");
        System.out.println("4.Search Customer");
        System.out.println("5.View Customer By ID");
        System.out.println("0.Return to Main menu");
        int choice = Util.chkInteger("", sc);

        switch (choice) {
                case 1:
                    currCust = register();
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    updateCustomers();
                    break;
                case 4:
                    searchCustomersByName();
                    break;
                case 5:
                    viewCustomersById();
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Wrong choice!");
        }
    }

    public String validUserName()
    {
        String userName = null;
        while(Util.chkStringNull(userName))
        {
            System.out.print("Enter the username:");
            userName = sc.nextLine();
        }
        return userName;
    }

    public int validAge()
    {
        String msg = "Enter the Age(age>18):";   
        int age = 0;     
        while (true) { 
            age = Util.chkInteger(msg, sc);
            if(Util.chlAge18(age))
                    break;
                else
                    System.out.println("Invalid age");
        }               
        return age ;
    }

    public String validMobileNumber()
    {
        String mobNo = null;
        while(!Util.chkVAlidMobileNumber(mobNo))
        {
            System.out.print("Enter the 10 digit MobileNumber:");
            mobNo = sc.nextLine();
        }
        return mobNo;
    }

    public String validEmail()
    {
        String email = null;
        while(!Util.validateEmail(email))
        {
            System.out.print("Enter the Valid Email:");
            email = sc.nextLine();
        }
        return email;
    }

    public String validAddress()
    {
        String address = null;
        while(Util.chkStringNull(address))
        {
            System.out.print("Enter the address:");
            address = sc.nextLine();
        }
        return address;
    }
    public Customer register() {
        System.out.println("Enter the below details to register");
        sc.nextLine(); // clear buffer

        String userName = validUserName();
        int age = validAge();        
        sc.nextLine(); // clear buffer
        String mobNo = validMobileNumber();
        String email = validEmail();     
        String address = validAddress();        

        Customer cust = null;
        System.out.println("User Created Successfully");
        cust = customerService.addCustomer(userName,age,mobNo,email,address);
        
        return cust;
    }


    public void viewAllCustomers() {
        List<Customer> custList = customerService.getAllCustomers();
        System.out.println("List Of All customer");
   //    String cust = String.format("%-5s %-10s %-5s %-15s %-15s %-20s",customerID,username,age,mobileNumber,email,address);
        String str = String.format("%-10s %-10s %-5s %-15s %-15s %-20s","CustomerID","Name","Age","MobileNumber","Email","Address");
        System.out.println(str);
          for(Customer cust: custList)
            System.out.println(cust);
    }

    public void  updateCustomers() {
        viewAllCustomers();
        String msg = "Enter the CustomerID u want to Update";
        int custId = Util.chkInteger(msg, sc);
        System.out.println("Enter The field(number) u want to update");
        System.out.println("1.Name");
        System.out.println("2.Age");
        System.out.println("3.Mobile Number");
        System.out.println("4.Address");
        int choice = Util.chkInteger(" ", sc);
        sc.nextLine();
        System.out.println("Enter the details to change");
        switch (choice) {
            case 1:
                String name = validUserName();
                customerService.updateNameByID(custId,name);
                break;
            case 2:
                int age = validAge();
                customerService.updateAgeByID(custId,age);
                break;
            case 3:
                String mno = validMobileNumber();
                customerService.updateMobileNoByID(custId,mno);
                break;
            case 4:
                String address = validAddress();
                customerService.updateAddressByID(custId,address);
                break;
            default:
                return;
        }
    }

    public void  searchCustomersByName() {
        String name = null;
        sc.nextLine();
        while(name == null || name.length() < 3)
        {
            System.out.println("Enter minimum 3 character to Search");
            name = sc.nextLine();
        }
        
        List<Customer> custList = customerService.searchByName(name);
        if(custList.isEmpty())
            System.out.println("No Customer found");
        else
        {
            System.out.println("List Of customer");
            String str = String.format("%-10s %-10s %-5s %-15s %-15s %-20s","CustomerID","Name","Age","MobileNumber","Email","Address");
            System.out.println(str);
            for(Customer cust: custList)
                System.out.println(cust);
        }
    }

    public void  viewCustomersById() {
        String msg = "Enter the CustomerID u want to View";
        int custId = Util.chkInteger(msg, sc);
        Customer cust = customerService.getCustomerById(custId);
        if(cust == null)
            System.out.println("Customer not found");
        else
            System.out.println(cust);
    }

}

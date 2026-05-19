package view;

import Util.Validation;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.Customer;
import service.CustomerService;

public class CustomerView {
    CustomerService custSrc;
    InventoryView invenView;
    Scanner sc;
    String fileName;
    public CustomerView(CustomerService custSrc,Scanner sc,String fileName,InventoryView invenView)
    {
        this.custSrc = custSrc;
        this.sc = sc;
        this.fileName = fileName;
        this.invenView = invenView;
    }

    public void addCustomerList() throws IOException
    {
        System.out.println("Creating Customer list...");
        custSrc.readCustFromFromFile(fileName);
        System.out.println("Customer Added Sucessfully!");
    }
    
    public void displayCustomers()
    {
        String str = String.format("%-15s %-10s %-10s %-10s ", "Email","Password","Name","Mobile");
        System.out.println(str);
        List<Customer> custList = custSrc.getAllCustomers();
        for(Customer cust:custList)
        {
            System.out.println(cust);
        }
    }

    public void registerCustomer() throws IOException {
        System.out.println("Enter the details below");
        sc.nextLine();
        //System.out.println("Enter the Email(should be Unique");
        String email = Validation.validateEmail(sc);
        if(custSrc.isAlreadyExists(email))
        {
            System.out.println("Email/Account already Exists!!");
            return;
        }
        String password = Validation.validatePassword(sc);
        String name = Validation.validateName(sc);
        long mobileNo = Validation.validateMobile(sc);
        String ePassword = custSrc.encryptPassword(password);
        Customer cust = custSrc.addCustomer(email,ePassword,name,mobileNo);
        custSrc.addToFile(email+" "+ePassword+" "+name+" "+mobileNo,fileName);
        if(cust != null)
            System.out.println("Customer Registerd Sucessfully!");
    }

    public Customer loginCustomer() {
        sc.nextLine();
        System.out.println("Enter the details below");
        String email = Validation.validateEmail(sc);
        if(custSrc.isAlreadyExists(email))
        {
            System.out.println("Enter the Password");
            String password = sc.next();
            if(custSrc.login(email,password))
            {
                System.out.println("User Logged in Successfully");
                System.out.println("Welcome "+email + "!");
                if("admin".equals(email))
                    invenView.viewAdminMenu();
                return custSrc.getCustomerByEmail(email);
            }
            else
                System.out.println("Password Incorrect!");
        }
        else
        {
            System.out.println("User DoesNot Exists. Please Register!");
            
        }
        return null;
    }    
}

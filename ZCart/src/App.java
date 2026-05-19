
import java.util.Scanner;
import model.Customer;
import repo.DBRepo;
import service.CustomerService;
import service.InventoryService;
import service.ShoppingService;
import view.CustomerView;
import view.InventoryView;
import view.ShoppingView;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Z-Cart!");
        String custfileName = "zusers_db.txt";
        String invenFileName = "z-kart_db.txt";
        Customer currCust = null;
        DBRepo dbRepo = new DBRepo();
        Scanner sc = new Scanner(System.in);
        CustomerService custSrc = new CustomerService(dbRepo);
        InventoryService invenSrc = new InventoryService(dbRepo);
        ShoppingService shoppingSrc = new ShoppingService(dbRepo);
        ShoppingView shoppingVw = new ShoppingView(shoppingSrc,sc,dbRepo);
        InventoryView invenVw = new InventoryView(invenSrc,sc, null);
        invenVw.addInventoryList(invenFileName);
        CustomerView custVw = new CustomerView(custSrc,sc,custfileName,invenVw);
        custVw.addCustomerList();
        
        while(true)
        {
            System.out.println("Enter ur option");
            System.out.println("1.View All Customers");
            System.out.println("2.View All Inventory");
            System.out.println("3.Register Customer");
            System.out.println("4.Login");
            System.out.println("0.Exit");
            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    custVw.displayCustomers();
                   break;
                case 2:
                    invenVw.printAllInventory();
                    break;
                case 3:
                    custVw.registerCustomer();
                    break;
                case 4:
                    currCust = custVw.loginCustomer();
                    if(currCust != null)
                        shoppingVw.start(currCust);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong Choice!");
            }

        }
    }
}
//zusers_db.txt

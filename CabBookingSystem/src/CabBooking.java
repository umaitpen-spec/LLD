
import java.util.Scanner;
import model.Customer;
import model.Driver;
import model.Location;

public class CabBooking {
    
    Scanner sc = new Scanner(System.in);
    CabService cSRc = new CabService();
    CabView cvw = new CabView(sc,cSRc);

    public void start()
    {
        intializeData();
        while(true)
        {
            System.out.println("Enter ur choice");
            System.out.println("1.Driver Login");
            System.out.println("2.Customer Login");
            System.out.println("3.Zula Adminstrator");
            System.out.println("0.Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    cvw.driverLogin();
                    break;
                case 2:
                    cvw.CustomerLogin();
                    break;
                case 3:
                    cvw.AdminLogin();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    private void intializeData() {
        

        Customer cust1 = new Customer(1, "WW","123", 111, 25);
        Customer cust2 = new Customer(2, "XX", "123",111, 25);
        Customer cust3 = new Customer(3, "YY", "123",111, 25);
        Customer cust4 = new Customer(4, "ZZ", "123",111, 25);
        
        cSRc.getCustList().put(cust1.getCustId(), cust1);
        cSRc.getCustList().put(cust2.getCustId(), cust2);
        cSRc.getCustList().put(cust3.getCustId(), cust3);
        cSRc.getCustList().put(cust4.getCustId(), cust4);

        Location loc1 = new Location(1, "A", 0);
        Location loc2 = new Location(3, "C", 4);
        Location loc3 = new Location(4, "D", 7);
        Location loc4 = new Location(6, "F", 9);
        Location loc5 = new Location(6, "B", 15);
        Location loc6 = new Location(6, "G", 18);
        Location loc7 = new Location(6, "H", 20);
        Location loc8 = new Location(6, "R", 23);


        cSRc.getLocList().put(loc1.getLocId(), loc1);
        cSRc.getLocList().put(loc2.getLocId(), loc2);
        cSRc.getLocList().put(loc3.getLocId(), loc3);
        cSRc.getLocList().put(loc4.getLocId(), loc4);
        cSRc.getLocList().put(loc5.getLocId(), loc5);
        cSRc.getLocList().put(loc6.getLocId(), loc6);
        cSRc.getLocList().put(loc7.getLocId(), loc7);
        cSRc.getLocList().put(loc8.getLocId(), loc8);

        Driver driver1 = new Driver(1, "aaa", "123",111, 25,1,loc3);
        Driver driver2 = new Driver(2, "bbb", "123",111, 36,2,loc6);
        Driver driver3 = new Driver(3, "ccc", "123",111, 28,3,loc7);
        Driver driver4 = new Driver(4, "ddd", "123",111, 31,4,loc1);
        
        cSRc.getDriverList().put(driver1.getDriverId(), driver1);
        cSRc.getDriverList().put(driver2.getDriverId(), driver2);
        cSRc.getDriverList().put(driver3.getDriverId(), driver3);
        cSRc.getDriverList().put(driver4.getDriverId(), driver4);
    }
}

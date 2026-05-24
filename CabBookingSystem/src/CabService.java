import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.CabHistory;
import model.Customer;
import model.Driver;
import model.Location;

public class CabService {
    Scanner sc = new Scanner(System.in);
    private Map<Integer,Customer> custList =  new HashMap<>();
    private Map<Integer,Driver> driverList =  new HashMap<>();
    private Map<Integer,Location> locList =  new HashMap<>();
    private List<CabHistory> hisList = new  ArrayList<>();

    Customer currCustomer = null;
    Driver currDriver = null;

    public Map<Integer, Customer> getCustList() {
        return custList;
    }
    public void setCustList(Map<Integer, Customer> custList) {
        this.custList = custList;
    }
    public Map<Integer, Driver> getDriverList() {
        return driverList;
    }
    public void setDriverList(Map<Integer, Driver> driverList) {
        this.driverList = driverList;
    }
    public Map<Integer, Location> getLocList() {
        return locList;
    }
    public void setLocList(Map<Integer, Location> locList) {
        this.locList = locList;
    }

    void driverLogin(String name, String password) {

        Driver driver = null;
        for(Driver dr: getDriverList().values())
        {
            if(dr.getName().equals(name) && dr.getPassword().equals(password))
            {
                driver = dr;
                break;
            }
        }
        currDriver = driver;
        if(currDriver == null)
            System.out.println("Invalid");
        else
        {
            System.out.println("Logged in successfully");
            int choice = util.chkInteger("Enter the choice");
               System.out.println("1.Get Summary");
              // System.out.println("View Booking History");
               System.out.println("0.Return to Main Menu");
               switch (choice) {
                   case 1:
                       cabHistory(currDriver);
                       break;
                    case 0:
                        return;
                   default:
                       System.out.println("Wrong Choice");
               }
        }
    }

    void custLogin(String name, String password) {
        Customer cust = null;
        for(Customer ct: getCustList().values())
        {
            if(ct.getName().equals(name) && ct.getPassword().equals(password))
            {
                cust = ct;
                break;
            }
        }
        currCustomer = cust;
        if(currCustomer == null)
        {
            System.out.println("Invalid");
            System.out.println("Plese enter the details to register");
            String msg = "CustomerId";
            int custId = util.chkInteger(msg);
            System.out.print("Enter name");
            String username = sc.next();
            msg = "Enter pass";
            int pass = util.chkInteger(msg);
            msg  = "Age";
            int age = util.chkInteger(msg);
            Customer cust1 = new Customer(custId, username, password, pass, age);
            custList.put(cust1.getCustId(),cust1);
            System.out.println("Customer Added Successfully");
        }
        else
        {
            System.out.println("Logged in successfully");
            
            while(true)
            {
               //System.out.println("Enter the choice");
               
               System.out.println("1.Book a Cab");
               System.out.println("2.View Booking History");
               System.out.println("0.Return to Main Menu");
               int choice = util.chkInteger("Enter the choice");
               switch (choice) {
                   case 1:
                       bookCab();
                       break;
                    case 2:
                        bookingHistory(currCustomer);
                        break;
                    case 0:
                        return;
                   default:
                       System.out.println("Wrong Choice");
               }
            }
            
        }
    }

    private void bookCab() {
        displayCab();
        displayLocation();
        Driver currCab = null;
        String msg = "Enter the Source LocationId";
        int slocId = util.chkInteger(msg);
        if(locList.get(slocId) != null)
        {
            msg = "Enter the Destination LocationId";
            int dlocId = util.chkInteger(msg);
            int minDist = 100,minTotTrip = 1000;
            if(locList.get(dlocId) != null)
            {
                //System.out.println("Enter the Destination LocationId");
                for(Driver dr:driverList.values())
                {
                    long totTripByCab = hisList.stream()
                                        .filter(a-> a.getDriver().equals(dr))
                                        .count();
                    if(hisList.isEmpty() || dr.getCabId() != hisList.get(hisList.size()-1).getDriver().getCabId())
                    {
                        if(dr.getCurrLocation().getLocId() == slocId && minTotTrip > totTripByCab)
                        {
                            currCab = dr;
                            minDist = 0;
                        }
                        else
                        {
                            int dist = Math.abs(dr.getCurrLocation().getDistFromOrigin() - locList.get(slocId).getDistFromOrigin());
                            if(dist <  minDist)
                            {
                                minDist = dist;
                                currCab = dr;
                            }
                            else if(dist == minDist)
                            {
                                if(minTotTrip > totTripByCab)
                                {
                                    currCab = dr;
                                }
                            }
                        }
                    }
                }
                
                if(currCab == null)
                {
                    System.out.println("No Cab Available");
                }
                else
                {
                    System.out.println("Assigned Cab:"+currCab.getName());
                    int  totFare = calculateFare(slocId,dlocId);
                    if(totFare > 0)
                    {
                        int commision = totFare * 30 / 100;
                        CabHistory cabHis = new CabHistory(locList.get(slocId),
                            locList.get(dlocId) ,
                             currCustomer, 
                             currCab, 
                             commision, 
                             totFare);
                        hisList.add(cabHis);
                    }
                }
            }
            else
                System.out.println("Wrong Location!");
        }
        else
            System.out.println("Wrong Location!");
    }

    private void displayCab() {
        System.out.println("Cab Details");
        System.out.println("Location | CabId");
        for(Driver dr:getDriverList().values())
        {
            System.out.println(dr.getCurrLocation().getName()+","+dr.getCabId());
        }
    }

    private void displayLocation() {
        System.out.println("Available Location");
        System.out.println("LocId | LocName");
        for(Location loc:getLocList().values())
        {
            System.out.println(loc.getLocId()+"|"+loc.getName());
        }
    }

    private int calculateFare(int slocId, int dlocId) {
        Location sLoc = locList.get(slocId);
        Location dLoc = locList.get(dlocId);
        int km = Math.abs(sLoc.getDistFromOrigin() - dLoc.getDistFromOrigin());
        int totFare = km * 10;
        System.out.println("Total Fare:"+totFare);
        String msg = "Do u accept the Fare 1.Yes/2.No";
        int op = util.chkInteger(msg);
        if(op == 1)
        {
            System.out.println("Cab Booked Successfully");
            return totFare;
        }   
        else
        {
            System.out.println("Better Luck next Time. C u Soon!");
        }
        return 0;
    }

    private void bookingHistory(Customer currCustomer) {
        System.out.println("CustomerId:"+currCustomer.getCustId());
        System.out.println("Customer Name:"+currCustomer.getName());

        System.out.println();
        List<CabHistory> cabHistorys = hisList.stream()
                                        .filter(a->a.getCustomer().equals(currCustomer))
                                        .collect(Collectors.toList());
        for(CabHistory ch:cabHistorys)
        {
            System.out.print(ch.getsLocation().getName() + "\t");
            System.out.print(ch.getdLocation().getName() + "\t");
            System.out.print(ch.getDriver().getCabId() + "\t");
            System.out.print(ch.getTotFare() + "\t");
        }
        System.out.println();
    }

    private void cabHistory(Driver driver) {
        System.out.println("CabId:"+ driver.getCabId());
        System.out.println("CabDriver Name:"+driver.getName());

        System.out.println();
        List<CabHistory> cabHistorys = hisList.stream()
                                        .filter(a->a.getDriver().equals(driver))
                                        .collect(Collectors.toList());

        for(CabHistory ch:cabHistorys)
        {
            System.out.print(ch.getsLocation().getName() + "\t");
            System.out.print(ch.getdLocation().getName() + "\t");
            System.out.print(ch.getCustomer().getName() + "\t");
            System.out.print(ch.getCommision() + "\t");
            System.out.print(ch.getTotFare() + "\t");
        }
        System.out.println();
    }

    void adminLogin(String name, String password) {
        if(name.equals("admin") && password.equals("admin"))
        {
            displaySummary();
        }
        else
            System.out.println("Wrong Credentials!!");
    }

    private void displaySummary() {
        System.out.println("Total Summary");
        for(Driver dr:driverList.values())
        {
            System.out.println("Cab Id"+dr.getCabId());
            List<CabHistory> cbList = hisList.stream()
                                .filter(a->a.getDriver().equals(dr)) 
                                .collect(Collectors.toList());
            System.out.println("Total Number of Trips: "+ cbList.size());
            System.out.println("Trip Details");
            int totCommision = 0,totFare = 0;
            for(CabHistory cb:cbList)
            {   
                System.out.print(cb.getsLocation().getName()+"\t");
                System.out.print(cb.getdLocation().getName()+"\t");
                System.out.print(cb.getCustomer().getName()+"\t");
                System.out.print(cb.getTotFare()+"\t");
                System.out.print(cb.getCommision()+"\t");
                totFare += cb.getTotFare();
                totCommision += cb.getCommision();
            }
            System.out.println();
            System.out.println("Total Fare Collected:"+totFare);
            System.out.println("Total ZULA Commission: "+totCommision);
        }

//         Cab Id: 1

// Total Number of Trips: 3

// Total Fare Collected: 290si

// Total ZULA Commission: 87

// Trip Details:
    }
}
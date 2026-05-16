package view;

import java.time.LocalTime;
import java.util.Scanner;
import model.Customer;
import model.Location;
import model.Restaurent;
import service.BookingService;
import service.CustomerService;
import service.LocationService;
import service.RestaurentService;

public class BookingView {

    BookingService bSrc;
    CustomerService cSrc;
    RestaurentService restSrc;
    LocationService locSrc;
    Scanner sc;
    public BookingView(BookingService bSrc,
        CustomerService cSrc,
        LocationService locSrc,
        RestaurentService restSrc,
        Scanner sc) {
        this.bSrc = bSrc;
        this.locSrc = locSrc;
        this.cSrc = cSrc;
        this.sc = sc;
        this.restSrc = restSrc;
    }

    
    public void makeBooking() {
        System.out.println("Enter Customer ID");
        int custID = sc.nextInt();
        Customer cust = cSrc.getCustomerByID(custID);
        if (cust == null) {
            System.out.println("Invalid Customer ID");
            return;
        }
        System.out.println("Enter Restaurent ID");
        int restID = sc.nextInt();
        Restaurent rest = restSrc.getRestaurentByID(restID);
        if (rest == null) {
            System.out.println("Invalid Restaurent ID");
            return;
        }
        System.out.println("Enter Destination Location ID");
        int locID = sc.nextInt();
        Location loc = locSrc.getLocationByID(locID);
        if (loc == null) {
            System.out.println("Invalid Location ID");
            return;
        }
        System.out.println("Enter Time of Booking");
        int time = sc.nextInt();
        if (time < 0 || time > 23) {
            System.out.println("Invalid Time of Booking");
            return;
        }
        bSrc.makeBooking(cust,rest,loc,time);
    }
}

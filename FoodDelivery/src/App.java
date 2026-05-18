
import java.util.Scanner;
import repo.DBrepo;
import service.BookingService;
import service.CustomerService;
import service.DEService;
import service.LocationService;
import service.RestaurentService;
import view.BookingView;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to food Delivery Booking!");
        DBrepo dBrepo = new DBrepo();
        Scanner sc  = new Scanner(System.in);
        LocationService lSrc = new LocationService(dBrepo);
        CustomerService customerService = new CustomerService(dBrepo, lSrc);
        DEService deSrc = new DEService(dBrepo);
        BookingService bSrc = new BookingService(dBrepo);
        RestaurentService rSrc = new RestaurentService(dBrepo,lSrc);
        BookingView bVw = new BookingView(bSrc,customerService,rSrc, sc);
        System.out.println("Seed Data:");
        System.out.println("Customers: 2000-2004");
        System.out.println("Restaurants: 5000-5004");
        System.out.println("Locations: 4000-4004");
        System.out.println("DEs: 3000-3004");
        while(true)
        {
            System.out.println("Enter your chioce");
            System.out.println("1.Booking");
            System.out.println("2.Display DE Activity");
            System.out.println("0.Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    bVw.makeBooking();
                    break;
                case 2:
                    deSrc.displayDE();
                    break;  
                case 0:
                    sc.close();
                    return;
                default:
                    System.out.println("Wrong choice!");
            }

        }
    }
}
